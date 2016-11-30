
$(".ajax-add-to-cart").click(function(){
    $.ajax({
        method: "POST",
        url: "/api/add-to-cart?id=" + $(this).data("id"),
        success: function (data) {
            console.log(data);
        }
    });

});
