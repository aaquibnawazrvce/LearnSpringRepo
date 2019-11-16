Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});
Ext.tip.QuickTipManager.init();
var reviewColumns = [ {
	header : 'Username',
	dataIndex : 'userName',
	sortable : false,
	width : 100
}, {
	header : 'Email',
	dataIndex : 'email',
	sortable : false,
	width : 100
}, {
	header : 'Login Type',
	dataIndex : 'loginType',
	sortable : false,
	width : 100,
	renderer : function(value, metadata, record, rowIndex, colIndex, store) {
		
		if(value==1){
			value ="Admin";
		}else if(value==3){
			value ="Supplier";
		}else if(value==2){
			value ="Consumer";
		}else{
			value ="Others";
		}
		
		return value;
}
}, {
	header : 'Phone No',
	dataIndex : 'phoneNo',
	sortable : false,
	width : 100
}, {
	header : 'Country',
	dataIndex : 'country',
	sortable : false,
	width : 100
}, {
	header : 'State',
	dataIndex : 'state',
	sortable : false,
	width : 100
} , {
	header : 'City',
	dataIndex : 'city',
	sortable : false,
	width : 100
}, {
	header : 'Age',
	dataIndex : 'age',
	sortable : false,
	width : 100
}];

var hideConfirmationMsg;
var showConfirmationMsg;
/* Hide the Confirmation Message */
hideConfirmationMsg = function() {
	var confMsgDiv = Ext.get('confirmationMessage');
	confMsgDiv.dom.innerHTML = "";
	confMsgDiv.dom.style.display = 'none';
}
/* Show Confirmation Message */
showConfirmationMsg = function(msg) {
	var confMsgDiv = Ext.get('confirmationMessage');
	confMsgDiv.dom.innerHTML = msg;
	confMsgDiv.dom.style.display = 'inline-block';
}

Ext.onReady(function() {
	Ext.define('reviewModel', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'id',
			mapping : 'id',
			type : 'int'
		}, {
			name : 'email',
			mapping : 'email',
			type : 'string'
		}, {
			name : 'phoneNo',
			mapping : 'phoneNo',
			type : 'string'
		}, {
			name : 'country',
			mapping : 'country',
			type : 'string'
		}, {
			name : 'state',
			mapping : 'state',
			type : 'string'
		}, {
			name : 'city',
			mapping : 'city',
			type : 'string'
		} , {
			name : 'city',
			mapping : 'city',
			type : 'string'
		}, {
			name : 'id',
			mapping : 'id',
			type : 'int'
		},{
			name : 'age',
			mapping : 'age',
			type : 'int'
		},{
			name : 'loginType',
			mapping : 'loginType',
			type : 'int'
		},{
			name : 'userName',
			mapping : 'userName',
			type : 'string'
		}]
	});

	var reviewStore = Ext.create('Ext.data.Store', {
		id : 'reviewStoreId',
		name : 'reviewStoreName',
		model : 'reviewModel',
		autoLoad : {
			start : 0,
			limit : 15
		},
		proxy : {
			type : 'ajax',
			url : contextPath + '/retriveAllUsers',
			actionMethods : {
				read : 'GET'
			},
			reader : {
				type : 'json',
				root : 'model',
				totalProperty : 'total'

			}
		},
		listeners : {
			'load' : function(store, records) {
			}
		},
		autoLoad : true
	});
	reviewStore.load();

	var reviewGrid = Ext.create('Ext.grid.Panel', {
		collapsible : true,
		title : 'Users',
		forceFit : true,
		id : 'reviewGrid',
		store : reviewStore,
		columns : reviewColumns,
		height : 400,
		width : 1200,
		autoFit : true,
		stripRows : true,
		renderTo : 'reviewGridContainer',
		bbar : Ext.create('Ext.PagingToolbar', {
			store : reviewStore,
			displayInfo : true,
			displayMsg : 'Displaying Users {0} - {1} of {2}',
			emptyMsg : "No Users to display",
			inputItemWidth : 35
		})
	});

});
