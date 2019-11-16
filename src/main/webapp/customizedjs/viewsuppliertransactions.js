Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});
Ext.tip.QuickTipManager.init();
var reviewColumns = [ {
	header : 'Product ID',
	dataIndex : 'productid',
	sortable : false,
	width : 100
}, {
	header : 'Product Name',
	dataIndex : 'productname',
	sortable : false,
	width : 100
}, {
	header : 'Product Type',
	dataIndex : 'producttype',
	sortable : false,
	width : 100,
	renderer : function(value, metadata, record, rowIndex, colIndex, store) {
		
		if(value==1){
			value ="Paintings";
		}else {
			value ="Sculpture";
		}
		return value;
	}
}, {
	header : 'Purchase User',
	dataIndex : 'loginid',
	sortable : false,
	width : 100
}, {
	header : 'Transaction Cost',
	dataIndex : 'transactioncost',
	sortable : false,
	width : 100
} , {
	header : 'Quantity',
	dataIndex : 'quantity',
	sortable : false,
	width : 100
}, {
	header : 'Delivery Address',
	dataIndex : 'deliveryaddress',
	sortable : false,
	width : 500,
	renderer : function(value, metadata, record, rowIndex, colIndex, store) {
			metadata.tdAttr = 'data-qtip="' + value + '"';
			return value;
	}
	
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
			name : 'productid',
			mapping : 'productid',
			type : 'int'
		} ,{
			name : 'producttype',
			mapping : 'producttype',
			type : 'int'
		}, {
			name : 'productname',
			mapping : 'productname',
			type : 'string'
		}, {
			name : 'supplierName',
			mapping : 'supplierName',
			type : 'string'
		}, {
			name : 'transactioncost',
			mapping : 'transactioncost',
			type : 'double'
		}, {
			name : 'quantity',
			mapping : 'quantity',
			type : 'int'
		}, {
			name : 'loginid',
			mapping : 'loginid',
			type : 'string'
		}, {
			name : 'deliveryaddress',
			mapping : 'deliveryaddress',
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
			url : contextPath + '/retriveSuppliersPurchase',
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
		title : 'Purchase History',
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
			displayMsg : 'Displaying Purchase History {0} - {1} of {2}',
			emptyMsg : "No Purchase History to display",
			inputItemWidth : 35
		})
	});

});
