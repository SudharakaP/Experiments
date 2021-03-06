var bodyParser = require('body-parser');
var express = require('express');
var Verify = require('./verify');

var leaderRouter = express.Router();
leaderRouter.use(bodyParser.json());

leaderRouter.route('/')
    .get(function(req, res, next){
    res.end('Will send all the leaders to you!');
})

    .post(Verify.verifyOrdinaryUser, Verify.verifyAdmin, function(req, res){
    res.end('Will add the leader: ' + req.body.name + ' with details: ' + req.body.description); 
})


    .delete(Verify.verifyOrdinaryUser, Verify.verifyAdmin, function(req, res){
    res.end('Deleting all leaders'); 
});

leaderRouter.route('/:leaderId')
.get(function(req,res,next){
    res.end('Will send details of the leader: ' + req.params.leaderId +' to you!');
})

    .put(Verify.verifyOrdinaryUser, Verify.verifyAdmin, function(req, res, next){
    res.write('Updating the leader: ' + req.params.leaderId + '\n');
    res.end('Will update the leader: ' + req.body.name + 
            ' with details: ' + req.body.description);
})

    .delete(Verify.verifyOrdinaryUser, Verify.verifyAdmin, function(req, res, next){
    res.end('Deleting leader: ' + req.params.leaderId);
});

module.exports = leaderRouter;