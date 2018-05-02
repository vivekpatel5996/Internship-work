var LocalStrategy = require('passport-local').Strategy;
var GoogleStrategy = require('passport-google-oauth').OAuth2Strategy;
var configAuth = require('./auth');

var User = require('../app/models/user');

module.exports = function (passport) {
  
      passport.serializeUser(function (user, done) {
            console.log("serializeUser");
            done(null, user.id);
            console.log("user.id "+user.id);

      });

      
      passport.deserializeUser(function (id, done) {
            console.log("deserializeUser");
            User.findById(id, function (err, user) {
                  console.log("user.id "+user.id);
                  done(err, user);
            });
      });

      passport.use('local-signup', new LocalStrategy({
                  usernameField: 'email',
                  passwordField: 'password',
                  passReqToCallback: true
            },
            function (req, email, password, done) {
                  process.nextTick(function () {
                        User.findOne({
                              'local.email': email
                        }, function (err, user) {

                              if (err)
                                    return done(err);

                              if (user) {
                                    console.log("User is already taken");
                                    return done(null, false, req.flash('signupMessage', 'That email is already taken'));
                              } else {
                                    var newUser = new User();

                                    newUser.local.email = email;
                                    newUser.local.password = newUser.generateHash(password);

                                    newUser.save(function (err) {
                                          if (err)
                                                throw err;
                                          return done(null, newUser);
                                    });
                              }

                        })
                  })
            }));



            
            passport.use('local-login',new LocalStrategy({
                  usernameField : 'email',
                  passwordField : 'password',
                  passReqToCallback : true
            },
            function(req,email,password,done){
                  console.log("In local login");
                  User.findOne({'local.email':email},function(err,user){
                        if(err)
                          return done(err);
                        if(!user)
                          return done(null,false,req.flash('loginMessage','No user find'));
                        
                        if(!user.validPassword(password)) 
                           return done(null,false,req.flash('loginMessage','Oops! Wrong password'));
                           
                        return done(null,user);   
                  })
            }
      ));


      passport.use(new GoogleStrategy({

            clientID        : configAuth.googleAuth.clientID,
            clientSecret    : configAuth.googleAuth.clientSecret,
            callbackURL     : configAuth.googleAuth.callbackURL,
    
        },
        function(token, refreshToken, profile, done) {
    
            console.log("token"+token+"\n");
            console.log(refreshToken      +"\n");
            process.nextTick(function() {
    
              
                User.findOne({ 'google.id' : profile.id }, function(err, user) {
                    if (err)
                        return done(err);
    
                    if (user) {
                        console.log(profile);
                        console.log("After");
                        return done(null, user);
                    } else {
                        var newUser          = new User();
                       // console.log(profile);
                        newUser.google.id    = profile.id;
                        newUser.google.token = token;
                        newUser.google.name  = profile.displayName;
                        newUser.google.email = profile.emails[0].value; 
                        newUser.save(function(err) {
                            if (err)
                                throw err;
                            return done(null, newUser);
                        });
                    }
                });
            });
      
        }));
    
}