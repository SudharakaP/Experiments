var bodyParser = require('body-parser');
var express = require('express');
var mongoose = require('mongoose');

var Favorites = require('../models/favorites');
var Verify = require('./verify');
var favoriteRouter = express.Router();
favoriteRouter.use(bodyParser.json());

favoriteRouter.route('/')
    .all(Verify.verifyOrdinaryUser)
    .get(function(req, res, next){
    Favorites.find({})
        .populate('postedBy')
        .populate('dishes')
        .exec(function(err, favorite){
        if(err) 
            throw err;
        res.json(favorite);
    });
})

    .post(function(req, res, next){

    Favorites.findOne({"postedBy": req.decoded._id}, function (err, favorite) {
        if (err) return next(err);
        if (favorite === null){
            Favorites.create({}, function (err, favorite) {
                if (err) return next(err);
                favorite.postedBy = req.decoded._id;
                favorite.dishes.push(req.body._id);
                favorite.save(function (err, favorite) {
                    if (err) throw err;
                    res.json(favorite);
                });
            });
        } else {
            if (favorite.dishes.indexOf(req.body._id, 0) == -1){
                favorite.dishes.push(req.body._id);
                favorite.save(function (err, favorite) {
                    if (err) throw err;
                    res.json(favorite);
                });
            } else {
                console.log("Has already been favorited");
                res.json(favorite);
            }
        }
    });
})

    .delete(function(req, res, next){
    Favorites.findOne({"postedBy": req.decoded._id}, function (err, favorite) {
        if (err) throw next(err);
        if (favorite !== null){
            favorite.remove();
            res.writeHead(200, {
                'Content-Type': 'text/plain'
            });
            res.end('Deleted the favorites for user with ID: ' + req.decoded._id);
        }
    });
});

favoriteRouter.route('/:dishObjectId')
    .delete(Verify.verifyOrdinaryUser, function(req, res, next){
    Favorites.findOne({"postedBy": req.decoded._id}, function (err, favorite) {      
        if (err) throw next(err);
        var id = favorite.dishes.indexOf(req.params.dishObjectId, 0);
        if (id !== -1){
            favorite.dishes.splice(id, 1);
            favorite.save(function (err, favorite) {
                if (err) throw err;
                res.json(favorite);
            });
        }
        res.json(favorite);
    });
});

module.exports = favoriteRouter;