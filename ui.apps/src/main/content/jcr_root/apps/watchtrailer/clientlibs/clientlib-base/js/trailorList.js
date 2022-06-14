$(document).ready(function(){

    loadData();


    $("#search_button_id").click(function(){
		 loadData();
    });

    function loadData(){

    var searchField = $("#search-field").val();

    var trailerhtml;
    var trailorouter= document.getElementById("content");
    var abc ;
		var count =0;
    $.ajax({ url: "/services/fetchTrailors?search_field="+searchField,
        contentType: "application/json",
        dataType: 'json',
        success: function(data){
           console.log(data);
            var dataCount = data.length;
			var trailercontent="";
		    if(data){
				for(var j=0;j<3;j++){
					var idCount ="boxid-"+j
					trailercontent +=
					'<div class="box" id="'+idCount+'">' +
					'<div class="head">' +
            			'<h2></h2>'
					'</div>'

					for(var i=0;i<6;i++){

            if(i==5 || count == dataCount){
				trailercontent  +=
             		'<div class="movie last">'
           }else{

					trailercontent  +=
           			'<div class="movie">'
           } 
           			trailercontent  +=
					'<div class="movie-image">' +
					'<span class="play"><span class="name">'+data[count].name+'</span></span>'+
					'<a href="#"><img src="'+data[count].imagePath +'" alt="" /></a> </div>' +
					'<div class="rating">' +
					'<p>RATING</p>'+
                    '<div class="stars">'+
                    '<div class="stars-in"> </div>'+
                   '</div>'+
                   '<span class="comments">'+data[count].comments+'</span> </div>'+
                   '<button id="'+data[count].id +'" type="button" class="show-video">Play Trailer</button>' +
					'</div>' 
					count++;
					if(i==5 || count == dataCount){
					trailercontent += 
						'<div class="cl">&nbsp;</div>'+
						'</div>'
				   }

			}
				}
				trailorouter.innerHTML = trailercontent;

			}

        }
    });
}


});






