$(document).ready(function(){


    // form
    var StarForm = new Vue({
        el: '#star-form',
        data: {
            name: "",
            x: "",
            y: "",
            typeId: 1,
            types: [],
            openerId: 1,
            openers: [],
            actionUpdate: false,
            newType: {
                name: "",
                removable: true
            },
            showTypeForm: false,
            newOpener: {
                name: ""
            },
            showOpenerForm: false,
        },
        mounted: function() {
            this.reload()
        },
        methods: {
            reload: function () {
                var self = this
                self.name = ""
                self.x = ""
                self.y = ""
                self.typeId = 1
                self.openerId = 1
                self.reloadTypes(false)
                self.reloadOpeners(false)
                self.actionUpdate = false
            },
            reloadTypes: function(getLast) {
                var self = this
                $.ajax({
                    url: '/api/types',
                    method: 'GET',
                    dataType: "json",
                    beforeSend: headers,
                    success: function (data) {
                        self.types = data;
                        if (getLast === false) self.typeId = data[0].id
                        else self.typeId = data[data.length - 1].id
                    }
                })
            },
            reloadOpeners: function(getLast) {
                var self = this
                $.ajax({
                    url: '/api/openers',
                    method: 'GET',
                    dataType: "json",
                    beforeSend: headers,
                    success: function (data) {
                        self.openers = data;
                        if (getLast === false) self.openerId = data[0].id
                        else self.openerId = data[data.length - 1].id
                    }
                })
            },
            save: function () {
                var self = this
                $.ajax({
                    url: '/api/stars',
                    method: 'POST',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({
                        name: self.name,
                        x: self.x,
                        y: self.y,
                        typeId: self.typeId,
                        openerId: self.openerId,
                        actionUpdate: false
                    }),
                    beforeSend: headers,
                    success: function () {
                        self.reload();
                        StarGrid.reload();
                    }
                });
            },
            // PUT update
            update: function (id) {
                var self = this
                $.ajax({
                    url: '/api/stars/' + id,
                    method: 'PUT',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({
                        name: self.name,
                        x: self.x,
                        y: self.y,
                        typeId: self.typeId,
                        openerId: self.openerId
                    }),
                    beforeSend: headers,
                    success: function () {
                        self.reload();
                        StarGrid.reload();
                    }
                });
            },

            getEditType: function() {
                StarForm.showTypeForm = true
            },
            closeEditType: function() {
                StarForm.showTypeForm = false
            },
            saveType: function() {
                var self = this
                self.showTypeForm = false
                $.ajax({
                    url: '/api/types',
                    method: 'POST',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({
                        name: self.newType.name,
                        removable: self.newType.removable
                    }),
                    beforeSend: headers,
                    success: function () {
                        self.reloadTypes(true);
                        self.newType.name = ""
                        self.newType.removable = true
                    }
                });
            },
            getEditOpener: function() {
                StarForm.showOpenerForm = true
            },
            closeEditOpener: function() {
                StarForm.showOpenerForm = false
            },
            saveOpener: function() {
                var self = this
                self.showOpenerForm = false
                $.ajax({
                    url: '/api/openers',
                    method: 'POST',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({
                        name: self.newOpener.name
                    }),
                    beforeSend: headers,
                    success: function () {
                        self.reloadOpeners(true);
                        self.newOpener.name = ""
                    }
                });
            },
        }
    });

    // grid
    var StarGrid = new Vue({
        el: '#star-grid',
        data: {
            stars: []
        },
        mounted: function() {
            this.reload();
        },
        methods: {
            reload: function() {
                var self = this
                $.ajax({
                    url: '/api/stars',
                    method: 'GET',
                    dataType: "json",
                    beforeSend: headers,
                    success: function (data) {
                        self.stars = data;
                    }
                });
            },
            // GET update
            edit: function(id){
                var self = this
                $.ajax({
                    url: '/api/stars/' + id,
                    method: 'GET',
                    dataType: "json",
                    beforeSend: headers,
                    success: function (data) {
                        StarForm.id = data.id
                        StarForm.name = data.name
                        StarForm.x = data.x
                        StarForm.y = data.y
                        StarForm.typeId = data.typeId
                        StarForm.openerId = data.openerId
                        StarForm.actionUpdate = true
                    }
                });
            },
            // DELETE action
            remove: function(id){
                var self = this
                $.ajax({
                    url: '/api/stars/' + id,
                    method: 'DELETE',
                    beforeSend: headers,
                    success: function () {
                        self.reload()
                    }
                });
            }
        }
    });

});

