Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();



var webColumns=[
	

         			{
         				header : 'Company Name',
         				dataIndex : 'thLevelEncryptCompanyName',
         				sortable:true,
         				width:140,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},
         			{
         				header : 'Insured Amount',
         				dataIndex : 'thLevelEncryptInsuredAmount',
         				sortable:true,
         				width    :140,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},
					{
         				header : 'Insurance ID',
         				dataIndex : 'thLevelEncryptInsuranceId',
         				sortable:true,
         				width    :230,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},
					{
         				header : 'Employee Id',
         				dataIndex : 'thLevelEncryptEmployeeId',
         				sortable:true,
         				width    :230,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},
					{
         				header : 'Insured Name',
         				dataIndex : 'thLevelEncryptInsuredName',
         				sortable:true,
         				width    :230,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},
					{
         				header : 'Group Name',
         				dataIndex : 'thLevelEncryptGroupName',
         				sortable:true,
         				width    :230,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},{
         				header : 'Start Date',
         				dataIndex : 'thLevelEncryptStartDate',
         				sortable:true,
         				width    :230,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},{
         				header : 'End Date',
         				dataIndex : 'thLevelEncryptEndDate',
         				sortable:true,
         				width    :230,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},{
         				header : 'Company Details',
         				dataIndex : 'thLevelEncryptCompanyDetails',
         				sortable:true,
         				width    :330,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},{
             				header : 'User Name',
             				dataIndex : 'username',
             				sortable:true,
             				width    :230
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
		confMsgDiv.dom.innerHTML =  msg;
		confMsgDiv.dom.style.display = 'inline-block';		
	}
	var webSiteStore;
Ext.onReady(function () {

	var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
	loadMask.show();
	
	Ext.define('webModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		          	{name:'id', mapping:'id',type:'int'},
		          	{name:'companyName', mapping:'companyName',type:'string'},
					{name:'companyDetails', mapping:'companyDetails',type:'string'},
					{name:'insuredAmount', mapping:'insuredAmount',type:'double'},
					{name:'startDate', mapping:'startDate',type:'string'},
					{name:'endDate', mapping:'endDate',type:'string'},
					{name:'insid', mapping:'insid',type:'string'},
					{name:'employeeId', mapping:'employeeId',type:'string'},
					{name:'insuranceId', mapping:'insuranceId',type:'string'},
					{name:'insuredName', mapping:'insuredName',type:'string'},
					{name:'groupName', mapping:'groupName',type:'string'},
					
					{name:'thEncPrescriptionInfoSummary', mapping:'thEncPrescriptionInfoSummary',type:'string'},
					{name:'thLevelEncryptCompanyName', mapping:'thLevelEncryptCompanyName',type:'string'},
					{name:'thLevelEncryptInsuredAmount', mapping:'thLevelEncryptInsuredAmount',type:'string'},
					{name:'thLevelEncryptStartDate', mapping:'thLevelEncryptStartDate',type:'string'},
					{name:'thLevelEncryptEndDate', mapping:'thLevelEncryptEndDate',type:'string'},
					{name:'thLevelEncryptInsid', mapping:'thLevelEncryptInsid',type:'string'},
					{name:'thLevelEncryptEmployeeId', mapping:'thLevelEncryptEmployeeId',type:'string'},
					{name:'thLevelEncryptInsuranceId', mapping:'thLevelEncryptInsuranceId',type:'string'},
					{name:'thLevelEncryptInsuredName', mapping:'thLevelEncryptInsuredName',type:'string'},
					{name:'thLevelEncryptGroupName', mapping:'thLevelEncryptGroupName',type:'string'},
					{name:'thLevelEncryptCompanyDetails', mapping:'thLevelEncryptCompanyDetails',type:'string'},
					
					
					{name:'encryptCompanyName', mapping:'encryptCompanyName',type:'string'},
					{name:'encryptCompanyDetails', mapping:'encryptCompanyDetails',type:'string'},
					{name:'encryptInsuredAmount', mapping:'encryptInsuredAmount',type:'double'},
					{name:'encryptStartDate', mapping:'encryptStartDate',type:'string'},
					{name:'encryptEndDate', mapping:'encryptEndDate',type:'string'},
					{name:'encryptInsid', mapping:'encryptInsid',type:'string'},
					{name:'encryptEmployeeId', mapping:'encryptEmployeeId',type:'string'},
					{name:'encryptInsuranceId', mapping:'encryptInsuranceId',type:'string'},
					{name:'encryptInsuredName', mapping:'encryptInsuredName',type:'string'},
					{name:'encryptGroupName', mapping:'encryptGroupName',type:'string'},
					{name:'username', mapping:'username',type:'string'}
				
					
				]
		
	});

	webStore = Ext.create('Ext.data.Store', {
		id : 'webSiteStoreId',
		name : 'webSiteStoreName',
		model : 'webModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/viewInsuranceInformationLevelAll',
			autoLoad: {start: 0, limit: 15},
			extraParams:{
			},
			actionMethods:{
				read:'GET'
			},
			reader : {
				type :'json',
				root:'model',
				totalProperty: 'total'
			}
		},
		listeners:
		{
			'load':function(store, records){
						
				loadMask.hide();
			}
		},
		autoLoad : true
	});
	
	
	
	
	
	var webSiteTableGrid = Ext.create('Ext.grid.Panel', {
		title:'Insurance Information',
		forceFit : true,
		id : 'webSiteGrid',
		store : webStore,
		columns : webColumns,
		width:1200,
		height:600,
		autoFit : true,
		autoscroll:true,
		stripRows:true,
		renderTo : Ext.getBody(),
		collapsible:true,
		overflowY:'auto',
		bbar: Ext.create('Ext.PagingToolbar', {
            store: webStore,
            displayInfo: true,
            displayMsg: 'Displaying One Level Insurance {0} - {1} of {2}',
            emptyMsg: "No One Level Insurance to display",
            inputItemWidth: 35
     })
	});

});
	
	
	
