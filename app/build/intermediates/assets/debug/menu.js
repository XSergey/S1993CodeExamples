$(document).ready(function(){
    $('#menu > ul > li').click(function(){
        console.log('click');
        $('#menu > ul > li > ul').removeClass('show');
        $(this).children('ul').addClass('show');
    });
    $('#faq').click(function(e){
        app.faqOpen();
        e.preventDefault();
    });
    $('#delivery_tracking').click(function(e){
        app.deliveryTrackingOpen();
        e.preventDefault();
    });
    $('#login').click(function(e){
        app.loginOpen();
        e.preventDefault();
    });
});