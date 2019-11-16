Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});
Ext.tip.QuickTipManager.init();
var reviewColumns = [ {
	header : 'Product ID',
	dataIndex : 'productId',
	sortable : false,
	width : 100
}, {
	header : 'Product Name',
	dataIndex : 'productName',
	sortable : false,
	width : 100
}, {
	header : 'Product Type',
	dataIndex : 'productType',
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
	header : 'Product Description',
	dataIndex : 'productDesc',
	sortable : false,
	width : 500,
	renderer : function(value, metadata, record, rowIndex, colIndex, store) {
			metadata.tdAttr = 'data-qtip="' + value + '"';
			return value;
	}
}, {
	header : 'Supplier Name',
	dataIndex : 'supplierName',
	sortable : false,
	width : 100
}, {
	header : 'Price',
	dataIndex : 'price',
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
			name : 'productId',
			mapping : 'productId',
			type : 'int'
		}, {
			name : 'productName',
			mapping : 'productName',
			type : 'string'
		}, {
			name : 'productType',
			mapping : 'productType',
			type : 'int'
		}, {
			name : 'productDesc',
			mapping : 'productDesc',
			type : 'string'
		}, {
			name : 'supplierName',
			mapping : 'supplierName',
			type : 'string'
		}, {
			name : 'price',
			mapping : 'price',
			type : 'double'
		} ]
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
			url : contextPath + '/retriveAllSupProducts',
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
		title : 'Products',
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
			displayMsg : 'Displaying Products {0} - {1} of {2}',
			emptyMsg : "No Products to display",
			inputItemWidth : 35
		})
	});

});
