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
         				header : 'Medical Information Summary',
         				dataIndex : 'encyptMedicalInfoSummary',
         				sortable:true,
         				width:240,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},
         			{
         				header : 'Medical Treatment Details',
         				dataIndex : 'encryptMedicalTreatmentDetails',
         				sortable:true,
         				width    :240,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
           				}
         			},
					{
         				header : 'Start Date',
         				dataIndex : 'encryptStartDate',
         				sortable:true,
         				width    :130
         			},
					{
         				header : 'End Date',
         				dataIndex : 'encryptEndDate',
         				sortable:true,
         				width    :130
         			},
					{
         				header : 'Doctor Name',
         				dataIndex : 'encryptDoctorName',
         				sortable:true,
         				width    :130
         			},
					{
         				header : 'Clinic Hospital Name',
         				dataIndex : 'encryptClinicHospitalName',
         				sortable:true,
         				width    :130
         			},{
         				header : 'Medical Info Id',
         				dataIndex : 'encyptMedicalInfoId',
         				sortable:true,
         				width    :130
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
		          	{name:'medicalInfoSummary', mapping:'medicalInfoSummary',type:'string'},
					{name:'medicalTreatmentDetails', mapping:'medicalTreatmentDetails',type:'string'},
					{name:'clinicHospitalName', mapping:'clinicHospitalName',type:'string'},
					{name:'startDate', mapping:'startDate',type:'string'},
					{name:'endDate', mapping:'endDate',type:'string'},
					{name:'doctorName', mapping:'doctorName',type:'string'},
					{name:'medicalInfoId', mapping:'medicalInfoId',type:'string'},
					{name:'userId', mapping:'userId',type:'string'},
					{name:'encyptMedicalInfoId', mapping:'encyptMedicalInfoId',type:'string'},
					{name:'encyptMedicalInfoSummary', mapping:'encyptMedicalInfoSummary',type:'string'},
					{name:'encryptMedicalTreatmentDetails', mapping:'encryptMedicalTreatmentDetails',type:'string'},
					{name:'encryptStartDate', mapping:'encryptStartDate',type:'string'},
					{name:'encryptEndDate', mapping:'encryptEndDate',type:'string'},
					{name:'encryptDoctorName', mapping:'encryptDoctorName',type:'string'},
					{name:'encryptClinicHospitalName', mapping:'encryptClinicHospitalName',type:'string'},
					{name:'thLevelEncryptMedicalInfoSummary', mapping:'thLevelEncryptMedicalInfoSummary',type:'string'},
					{name:'thLevelEncryptMedicalTreatmentDetails', mapping:'thLevelEncryptMedicalTreatmentDetails',type:'string'},
					{name:'thLevelEncryptStartDate', mapping:'thLevelEncryptStartDate',type:'string'},
					{name:'thLevelEncryptEndDate', mapping:'thLevelEncryptEndDate',type:'string'},
					{name:'thLevelEncryptDoctorName', mapping:'thLevelEncryptDoctorName',type:'string'},
					{name:'thLevelEncryptClinicHospitalName', mapping:'thLevelEncryptClinicHospitalName',type:'string'},
					{name:'thLevelEncryptMedicalInfoId', mapping:'thLevelEncryptMedicalInfoId',type:'string'}
				]
		
	});

	webStore = Ext.create('Ext.data.Store', {
		id : 'webSiteStoreId',
		name : 'webSiteStoreName',
		model : 'webModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/viewMedicalInformationLevelAll',
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
		title:'Medical Information',
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
            displayMsg: 'Displaying Medical Information {0} - {1} of {2}',
            emptyMsg: "No Medical Information to display",
            inputItemWidth: 35
     })
	});

});
	
	
	
