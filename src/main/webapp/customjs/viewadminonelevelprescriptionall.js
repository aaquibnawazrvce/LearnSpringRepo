Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();



var webColumns=[
	
						{
						header : 'User Id',
						dataIndex : 'userId',
						sortable:true,
						width    :130
						},
         			{
         				header : 'Prescription Info Summary',
         				dataIndex : 'encPrescriptionInfoSummary',
         				sortable:true,
         				width:240,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},
         			{
         				header : 'Prescription Treatment Details',
         				dataIndex : 'encPrescriptionTreatmentDetails',
         				sortable:true,
         				width    :200,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},
					{
         				header : 'Start Date',
         				dataIndex : 'encStartDate',
         				sortable:true,
         				width    :130
         			},
					{
         				header : 'End Date',
         				dataIndex : 'encEndDate',
         				sortable:true,
         				width    :130
         			},
					{
         				header : 'Doctor Name',
         				dataIndex : 'encDoctorName',
         				sortable:true,
         				width    :150
         			},
					{
         				header : 'Clinic Hospital Name',
         				dataIndex : 'encClinicHospitalName',
         				sortable:true,
         				width    :180
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
		          	{name:'thEncPrescriptionInfoId', mapping:'thEncPrescriptionInfoId',type:'string'},
					{name:'encPrescriptionInfoId', mapping:'encPrescriptionInfoId',type:'string'},
					{name:'prescriptionInfoId', mapping:'prescriptionInfoId',type:'string'},
					{name:'thEncClinicHospitalName', mapping:'thEncClinicHospitalName',type:'string'},
					{name:'thEncDoctorName', mapping:'thEncDoctorName',type:'string'},
					{name:'thEncEndDate', mapping:'thEncEndDate',type:'string'},
					{name:'thEncStartDate', mapping:'thEncStartDate',type:'string'},
					{name:'thEncPrescriptionTreatmentDetails', mapping:'thEncPrescriptionTreatmentDetails',type:'string'},
					{name:'thEncPrescriptionInfoSummary', mapping:'thEncPrescriptionInfoSummary',type:'string'},
					{name:'encClinicHospitalName', mapping:'encClinicHospitalName',type:'string'},
					{name:'encDoctorName', mapping:'encDoctorName',type:'string'},
					{name:'encEndDate', mapping:'encEndDate',type:'string'},
					{name:'encStartDate', mapping:'encStartDate',type:'string'},
					{name:'encPrescriptionTreatmentDetails', mapping:'encPrescriptionTreatmentDetails',type:'string'},
					{name:'encPrescriptionInfoSummary', mapping:'encPrescriptionInfoSummary',type:'string'},
					{name:'clinicHospitalName', mapping:'clinicHospitalName',type:'string'},
					{name:'doctorName', mapping:'doctorName',type:'string'},
					{name:'endDate', mapping:'endDate',type:'string'},
					{name:'startDate', mapping:'startDate',type:'string'},
					{name:'prescriptionTreatmentDetails', mapping:'prescriptionTreatmentDetails',type:'string'},
					{name:'userId', mapping:'userId',type:'string'},
					{name:'prescriptionInfoSummary', mapping:'prescriptionInfoSummary',type:'string'},
					{name:'userId', mapping:'userId',type:'string'}
				]
		
	});

	webStore = Ext.create('Ext.data.Store', {
		id : 'webSiteStoreId',
		name : 'webSiteStoreName',
		model : 'webModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/viewPrescriptionInformationLevelAll',
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
		title:'View Prescription Information',
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
            displayMsg: 'Displaying Prescription Information {0} - {1} of {2}',
            emptyMsg: "No Prescription Information to display",
            inputItemWidth: 35
     })
	});

});
	
	
	
