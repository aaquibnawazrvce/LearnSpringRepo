Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});
Ext.tip.QuickTipManager.init();
var reviewColumns = [ {
	header : 'Account Number',
	dataIndex : 'accountno',
	sortable : false,
	width : 100
}, {
	header : 'IPIN',
	dataIndex : 'ipin',
	sortable : false,
	width : 100
}, {
	header : 'Balance',
	dataIndex : 'balance',
	sortable : false,
	width : 100
} ];

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
			name : 'accountno',
			mapping : 'accountno',
			type : 'string'
		}, {
			name : 'balance',
			mapping : 'balance',
			type : 'double'
		}, {
			name : 'ipin',
			mapping : 'ipin',
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
			url : contextPath + '/retriveAllAccounts',
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
		title : 'Accounts',
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
			displayMsg : 'Displaying Accounts {0} - {1} of {2}',
			emptyMsg : "No Accounts to display",
			inputItemWidth : 35
		})
	});

});
