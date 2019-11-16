Ext.require('Ext.chart.*');
Ext.require('Ext.layout.container.Fit');
Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});



Ext.onReady(function () {
	
	Ext.define('securityModel', {
		extend : 'Ext.data.Model',
		fields : [ 
		           {name:'id', mapping:'id',type:'int'},
		           {name:'levelAlgo1', mapping:'levelAlgo1',type:'double'},
		           {name:'levelAlgo2', mapping:'levelAlgo2',type:'double'},
		           {name:'algo1Time', mapping:'algo1Time',type:'double'},
		           {name:'algo2Time', mapping:'algo2Time',type:'double'}
				  ],
		idProperty: 'id'
	});
	
	var securityLevelStore = Ext.create('Ext.data.Store', {
		id : 'energyCompareStoreId1',
		name : 'energyCompareStoreName1',
		model : 'securityModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/comparesecuritylevels',
			actionMethods:{
				read:'POST'
			},
			reader : {
				type :'json',
				root:'model',
				totalProperty:'total'
			}
		},
		listeners:
		{
			'load':function(store, records){
		}			
		},
		autoLoad : true
	});
securityLevelStore.load();
	
	Ext.create('Ext.chart.Chart', {
		   renderTo: 'content',
		   width: 400,
		   height: 300,
		   store: securityLevelStore,
		   legend: {
			    position: 'right'
			},
		   axes: [
		          {
		              title: 'Time Taken(ms)',
		              type: 'Numeric',
		              position: 'left',
		              fields: ['algo1Time','algo2Time']
		            
		          },
		          {
		              title: 'Iteration No',
		              type: 'Numeric',
		              position: 'bottom',
		              fields: ['id']
		          }
		      ],
		      series: [
		               {
		                   type: 'line',
		                   xField: 'id',
		                   yField: 'algo1Time',
		                   title:'Time Taken Policy Based '
		 		              
		               },
		               {
		                   type: 'line',
		                   xField: 'id',
		                   yField: 'algo2Time',
		                   title:' Time Taken 3 Level Policy Based'
		 		              
		               }
		           ]
		});

});    
           