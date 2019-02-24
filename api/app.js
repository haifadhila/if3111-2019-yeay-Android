var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mongoose = require('mongoose');

Task = require('./model/task');
User = require('./model/user');
Cat = require('./model/cat');

mongoose.connect('mongodb+srv://nadija:161998@cat-manager-kvakv.mongodb.net/test?retryWrites=true');
var db = mongoose.connection;

app.use(bodyParser.json());

app.get('/', function(req, res){
    res.send('Please use /api/task, /api/cat, or /api/user');
});

app.get('/api/user', function(req, res){
    User.getAllUsers(function(err, users){
        if(err){
            throw err;
        }
        res.json({users: users});
        console.log("users found.")
    });
});

app.get('/api/user/:_id', function(req, res){
    var id = req.params._id;
    User.getUserById(id, function(err, user){
        if(err){
            throw err;
        }
        res.json({user: user});
    });
});

app.get('/api/task', function(req, res){
    Task.getAllTasks(function(err, tasks){
        if(err){
            throw err;
        }
        res.json({tasks:tasks});
    });
});

app.get('/api/task/:_id', function(req, res){
    var id = req.params._id;
    Task.getTaskById(id, function(err, task){
        if(err){
            throw err;
        }
        res.json({task: task});
    });
});

app.get('/api/cat', function(req, res){
    Cat.getAllCats(function(err, cats){
        if(err){
            throw err;
        }
        res.json({cats: cats});
    });
});

app.get('/api/cat/:_id', function(req, res){
    var id = req.params._id;
    Cat.getCatById(id, function(err, cat){
        if(err){
            throw err;
        }
        res.json({cat: cat});
    });
});


app.post('/api/cat', function(req, res){
    var cat = req.body;
    Cat.addCat(cat, function(err, cat){
        if(err){
            throw err;
        }
        res.json(cat);
    });
});

app.listen(process.env.PORT || 3000);
console.log('Running on port 3000');