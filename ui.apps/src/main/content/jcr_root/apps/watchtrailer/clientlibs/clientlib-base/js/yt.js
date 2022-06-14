
jQuery(document).ready(function ($) {
            $('.show-video').on('click', function () {
			     var id = this.id;

                $.showYtVideo({
                    videoId: id
                });
            });
        });