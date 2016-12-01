var products = [];

if (products.length < 1) {
    $('#buyProductsButton').addClass("hidden");
}

$(".ajax-add-to-cart").click(function () {
    $('#buyProductsButton').removeClass("hidden");
    var orderedNumber = parseInt($(this).data("ordered-number")) + 1;
    $(this).data("ordered-number", orderedNumber);
    products.push($(this).data("id"));
    //$(this).next(".added-to-cart").html('<br/> added to cart : ' + $(this).data("ordered-number"));
    /*$.ajax({
     method: "POST",
     url: "/api/add-to-cart?id=" + $(this).data("id"),
     success: function (data) {
     console.log(data);
     }
     });*/
});
$('#buyProductsButton').click(function(){
    $.ajax({
        method: "POST",
        url: "/api/get-total-price?products=" + products,
        success: function (data) {
            $('#total-price').html("Total : " + data);
        }
    });
});
$("#creditCardValidation").submit(function(e){
    e.preventDefault();
    var dataForm = $(this).serialize();
    console.log("/validate-credit-card?" + dataForm);
    $.ajax({
        method: "POST",
        url: "/submit-order?products=" + products,
        success: function (data) {
            $('nav').after(
                '<div class="alert alert-success alert-dismissible" role="alert">' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
                '<p>'+ data + '</p>' +
                '</div>');
            $.ajax({
                method: "POST",
                url: "/validate-credit-card?" + dataForm,
                success: function (data) {
                    $('.modal').hide();
                }
            });
        }
    });
});