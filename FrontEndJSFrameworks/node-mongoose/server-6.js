var mongoose = require('mongoose'),
    assert = require('assert');

var Leaders = require('./models/leadership');

// Connection URL
var url = 'mongodb://localhost:27017/conFusion';mongoose.connect(url);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function () {
    // we're connected!
    console.log("Connected correctly to server");

    // create a new leader
    Leaders.create({
        name: 'Tony',
        image: 'sdfsdfsdf',
        designation: 'Mr.',
        abbr: 'Tonniy',
        description: 'Test'
    }, function (err, leader) {
        if (err) throw err;
        console.log('Leader created!');
        console.log(leader);

        var id = leader._id;

        // get all the leaders
        setTimeout(function () {
            Leaders.findByIdAndUpdate(id, {
                    $set: {
                        description: 'Updated Test',
                        label: 'Test Label'
                    }
                }, {
                    new: true
                })
                .exec(function (err, leader) {
                    if (err) throw err;
                    console.log('Updated leader!');
                    console.log(leader);

                    leader.save(function (err, leader) {
                        db.collection('leaders').drop(function () {
                            db.close();
                        });
                    });
                });
        }, 3000);
    });
});