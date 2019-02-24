var mongoose = require('mongoose');

var catSchema = mongoose.Schema({
   name:{
        type: String,
        required: true
    },
    img:{
        type: String,
        required: true
    },
    date_time:{
        type: Date,
        required: true
    },
    longitude:{
        type: String,
        required: true
    },
    latitude:{
        type: String,
        required: true
    },

});

var Cat = module.exports = mongoose.model('Cat', catSchema);

module.exports.getAllCats = function(callback, limit){
    Cat.find(callback).limit(limit);
}

module.exports.getCatById = function(id, callback){
    Cat.findById(id, callback);
}

module.exports.addCat = function(cat, callback) {
    Cat.create(cat, callback);
}