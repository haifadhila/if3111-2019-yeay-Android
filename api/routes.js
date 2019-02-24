'use strict';

module.exports = function(app) {
    var todoList = require('./controller');

    app.route('/')
        .get(todoList.index);

    app.route('/users/:user_id')
        .get(todoList.findUser);

    app.route('/task')
        .post(todoList.createTask);

    app.route('/task')
        .get(todoList.getAllTask);
    
    app.route('/task/:task_id')
        .get(todoList.getTask)
        .delete(todoList.deleteTask);
        
    app.route('/cat')
        .post(todoList.createCat)
        .get(todoList.getAllCat);
    
    app.route('/cat/:cat_id')
        .get(todoList.getCat);
};