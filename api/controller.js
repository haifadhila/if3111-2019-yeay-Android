'use strict';

var response = require('./res');
var connection = require('./conn');

exports.findUser = function(req, res) {
    var username = req.body.username;
    var password = req.body.password;

    connection.query('SELECT * FROM user WHERE username = ? and password = ?', [username, password], function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok("User ditemukan!", res)
        }
    });
};

exports.createTask = function(req, res) {
    var title = req.body.title;
    var date_time = req.body.date_time;

    connection.query('INSERT INTO task (title, date_time) values (?,?)', [title, date_time], function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok("Task berhasil ditambahkan.", res)
        }
    });
};

exports.getAllTask = function(req, res) {
    connection.query('SELECT * FROM task', function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok("Berhasil menampilkan seluruh task.", res)
        }
    });
};

exports.getTask = function(req, res) {
    var task_id = req.body.id;
    connection.query('SELECT * FROM task WHERE id=?', [task_id], function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok("Berhasil menampilkan task.", res)
        }
    });
};

exports.deleteTask = function(req, res) {
    var task_id = req.body.id;
    connection.query('DELETE FROM task WHERE id=?', [task_id], function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok("Berhasil menghapus Task!", res)
        }
    });
};


exports.createCat = function(req, res) {
    var img = req.body.title;
    var date_time = req.body.date_time;
    var longitude = req.body.longitude;
    var latitude = req.body.latitude;

    connection.query('INSERT INTO cat (img, date_time, longitude, latitude) values (?,?,?,?)', [img, date_time, longitude, latitude], function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok("Cat berhasil ditambahkan.", res)
        }
    });
};

exports.getAllCat = function(req, res) {
    connection.query('SELECT * FROM cat', function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok("Berhasil menampilkan seluruh cat.", res)
        }
    });
};

exports.getCat = function(req, res) {
    var cat_id = req.body.id;
    connection.query('SELECT * FROM task WHERE id=?', [cat_id], function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok("Berhasil menampilkan sebuah cat.", res)
        }
    });
};

exports.index = function(req, res) {
    response.ok("Hello from the Node JS RESTful side!", res)
};