Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});
Ext.tip.QuickTipManager.init();
var reviewColumns = [ {
	header : 'Supplier Name',
	dataIndex : 'supplierName',
	sortable : false,
	width : 100
}, {
	header : 'Total Revenue',
	dataIndex : 'totalRevenue',
	sortable : false,
	width : 100
} , {
	header : 'Average Revenue',
	dataIndex : 'avgRevenue',
	sortable : false,
	width : 100
}	
];

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
			name : 'supplierName',
			mapping : 'supplierName',
			type : 'string'
		}, {
			name : 'totalRevenue',
			mapping : 'totalRevenue',
			type : 'double'
		}, {
			name : 'avgRevenue',
			mapping : 'avgRevenue',
			type : 'double'
		}]
	});

	var reviewStore = Ext.create('Ext.data.Store', {
		id : 'reviewStoreId',
		name : 'reviewStoreName',
		model : 'reviewModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/retriveTotalPurchase',
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
		title : 'Total Revenue Report',
		forceFit : true,
		id : 'reviewGrid',
		store : reviewStore,
		columns : reviewColumns,
		height : 400,
		width : 1200,
		autoFit : true,
		stripRows : true,
		renderTo : 'reviewGridContainer'
	});

});
