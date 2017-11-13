var dishRouter = require('./dishRouter.js'),
    promoRouter = require('./promoRouter.js'),
    leaderRouter = require('./leaderRouter.js'),
    express = require('express');

var hostname = 'localhost';
var port = 3000;

var app = express();

app.use('/dishes', dishRouter.dishRouter());
app.use('/leaders', leaderRouter.leaderRouter());
app.use('/promotions', promoRouter.promoRouter());

app.use(express.static(__dirname + '/public'));

app.listen(port, hostname, function(){
    console.log(`Server running at http://${hostname}:${port}/`);
});