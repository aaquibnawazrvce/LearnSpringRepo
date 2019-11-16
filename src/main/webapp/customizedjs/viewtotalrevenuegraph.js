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

	Ext.create('Ext.chart.Chart', {
	    renderTo: 'chartContainer',
	    width: 500,
	    height: 300,
	    animate: true,
	    store: reviewStore,
	    axes: [{
	        type: 'Numeric',
	        position: 'bottom',
	        fields: ['totalRevenue'],
	        label: {
	            renderer: Ext.util.Format.numberRenderer('0,0')
	        },
	        title: 'Total Revenue',
	        grid: true,
	        minimum: 0
	    }, {
	        type: 'Category',
	        position: 'left',
	        fields: ['supplierName'],
	        title: 'Supplier Name'
	    }],
	    series: [{
	        type: 'bar',
	        axis: 'bottom',
	        highlight: true,
	        tips: {
	          trackMouse: true,
	          width: 140,
	          height: 28,
	          renderer: function(storeItem, item) {
	            this.setTitle(storeItem.get('supplierName') + ': ' + storeItem.get('totalRevenue'));
	          }
	        },
	        label: {
	          display: 'insideEnd',
	            field: 'totalRevenue',
	            renderer: Ext.util.Format.numberRenderer('0'),
	            orientation: 'horizontal',
	            color: '#333',
	            'text-anchor': 'middle'
	        },
	        xField: 'supplierName',
	        yField: 'totalRevenue'
	    }]
	});
	
	Ext.create('Ext.chart.Chart', {
	    renderTo: 'chart1Container',
	    width: 500,
	    height: 300,
	    animate: true,
	    store: reviewStore,
	    axes: [{
	        type: 'Numeric',
	        position: 'bottom',
	        fields: ['avgRevenue'],
	        label: {
	            renderer: Ext.util.Format.numberRenderer('0,0')
	        },
	        title: 'Average Revenue',
	        grid: true,
	        minimum: 0
	    }, {
	        type: 'Category',
	        position: 'left',
	        fields: ['supplierName'],
	        title: 'Supplier Name'
	    }],
	    series: [{
	        type: 'bar',
	        axis: 'bottom',
	        highlight: true,
	        tips: {
	          trackMouse: true,
	          width: 140,
	          height: 28,
	          renderer: function(storeItem, item) {
	            this.setTitle(storeItem.get('supplierName') + ': ' + storeItem.get('avgRevenue'));
	          }
	        },
	        label: {
	          display: 'insideEnd',
	            field: 'avgRevenue',
	            renderer: Ext.util.Format.numberRenderer('0'),
	            orientation: 'horizontal',
	            color: '#333',
	            'text-anchor': 'middle'
	        },
	        xField: 'supplierName',
	        yField: 'avgRevenue'
	    }]
	});

});
