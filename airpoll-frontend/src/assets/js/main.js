$( document ).ready(function() {
  if ($(window).width() < 992) {
    $('#header').addClass('header-scrolled');
  } else {
    $('#header').removeClass('header-scrolled');
  }
});

$(window).on('resize', function() {
  if ($(window).width() < 992) {
    $('#header').addClass('header-scrolled');
  } else if (window.location.href.indexOf("home") > -1) {
    $('#header').removeClass('header-scrolled');
  }
});


$(document).on("click", "a", function() {
  // if (document.documentElement.scrollHeight !== document.documentElement.clientHeight) {
  if (window.location.href.indexOf("home") > -1 && ($(window).width() > 992)) {
    $('#header').removeClass('header-scrolled');
  } else {
    $('#header').addClass('header-scrolled');
  }

  if ($('.nav-menu.d-none.d-lg-block').hasClass('display')) $('.nav-menu.d-none.d-lg-block').toggleClass('display');
});

$(document).on('click', 'i.icofont-navigation-menu', function(e) {
  $('.nav-menu.d-none.d-lg-block').toggleClass('display');
});