var mongoose = require('mongoose');

var taskSchema = mongoose.Schema({
    title:{
        type: String,
        required: true
    },
    date_time:{
        type: Date,
        required: true
    }
});

var Task = module.exports = mongoose.model('Task', taskSchema);

module.exports.getAllTasks = function(callback, limit){
    Task.find(callback).limit(limit);
}

module.exports.getTaskById = function(id, callback){
    Task.findById(id, callback);
}