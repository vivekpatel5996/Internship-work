module.exports = function(passport){
 
    /* GET login page. */
    router.get('/', function(req, res) {
      // Display the Login page with any flash message, if any
      res.render('index', { message: req.flash('message') });
    });
   
    /* Handle Login POST */
    router.post('/login', passport.authenticate('login', {
      successRedirect: '/home',
      failureRedirect: '/',
      failureFlash : true 
    }));
    return router;
}