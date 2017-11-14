var mongoose = require('mongoose'),
    assert = require('assert');

var Promotions = require('./models/promotions');

// Connection URL
var url = 'mongodb://localhost:27017/conFusion';mongoose.connect(url);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function () {
    // we're connected!
    console.log("Connected correctly to server");

    // create a new promotion
    Promotions.create({
        name: 'Uthapizza',
        image: 'sdfsdfsdf',
        category: 'Main',
        price: '$3.674545',
        description: 'Test'
    }, function (err, promo) {
        if (err) throw err;
        console.log('Promotion created!');
        console.log(promo);

        var id = promo._id;

        // get all the promotions
        setTimeout(function () {
            Promotions.findByIdAndUpdate(id, {
                    $set: {
                        description: 'Updated Test',
                        label: 'Test Label'
                    }
                }, {
                    new: true
                })
                .exec(function (err, promo) {
                    if (err) throw err;
                    console.log('Updated Promotion!');
                    console.log(promo);

                    promo.save(function (err, promo) {
                        db.collection('promotions').drop(function () {
                            db.close();
                        });
                    });
                });
        }, 3000);
    });
});