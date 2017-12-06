var bodyParser = require('body-parser');
var express = require('express');
var Verify = require('./verify');

var promoRouter = express.Router();
promoRouter.use(bodyParser.json());

promoRouter.route('/')

    .get(function(req, res, next){
    res.end('Will send all the promotions to you!');
})

    .post(Verify.verifyOrdinaryUser, Verify.verifyAdmin, function(req, res){
    res.end('Will add the promotions: ' + req.body.name + ' with details: ' + req.body.description); 
})


    .delete(Verify.verifyOrdinaryUser, Verify.verifyAdmin, function(req, res){
    res.end('Deleting all promotions'); 
});

promoRouter.route('/:promoId')

    .get(function(req,res,next){
    res.end('Will send details of the promotion: ' + req.params.promoId +' to you!');
})

    .put(Verify.verifyOrdinaryUser, Verify.verifyAdmin, function(req, res, next){
    res.write('Updating the promotion: ' + req.params.promoId + '\n');
    res.end('Will update the promotion: ' + req.body.name + 
            ' with details: ' + req.body.description);
})

    .delete(Verify.verifyOrdinaryUser, Verify.verifyAdmin, function(req, res, next){
    res.end('Deleting promotion: ' + req.params.promoId);
});

module.exports = promoRouter;