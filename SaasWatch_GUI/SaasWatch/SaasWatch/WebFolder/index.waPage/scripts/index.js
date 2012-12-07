



WAF.onAfterInit = function onAfterInit() {// @lock

// @region namespaceDeclaration// @startlock
	var checkbox2 = {};	// @checkbox
	var button2 = {};	// @button
	var imageButton3 = {};	// @buttonImage
	var button5 = {};	// @button
	var button4 = {};	// @button
	var startHistoryTracking = {};	// @button
	var imageButton2 = {};	// @buttonImage
	var imageButton1 = {};	// @buttonImage
	var combobox2 = {};	// @combobox
	var setting = {};	// @button
	var documentEvent = {};	// @document
	var passwordField = {};	// @textField
	var button1 = {};	// @button
// @endregion// @endlock
var chart1; // globally available
$(document).ready(function() {
      chart1 = new Highcharts.Chart({
         chart: {
            renderTo: 'container1',
            type: 'line'
         },
         title: {
            text: 'CPU Metrics'
         },
         xAxis: {
            categories: ['0', '1', '2',"3","4","5","6","7","8","9","10","11","12"]
         },
         yAxis: {
            title: {
               text: 'Percentage'
            }
         },
//         series: [{
//            name: 'SaasWatch',
//            data: [1,2,4]
//         }, {
//            name: 'Amazon Metrics',
//            data: [5, 7, 3]
//         }]
      });
      
      chart2 = new Highcharts.Chart({
         chart: {
            renderTo: 'container3',
            type: 'line'
         },
         title: {
            text: 'Memory Metrics'
         },
         xAxis: {
            categories: ['0', '1', '2',"3","4","5","6","7","8","9","10","11","12"]
         },
         yAxis: {
            title: {
               text: 'usage'
            }
         }
      });
      
      chart3 = new Highcharts.Chart({
         chart: {
            renderTo: 'container10',
            type: 'line'
         },
         title: {
            text: 'Disk Metrics'
         },
         xAxis: {
            categories: ['0', '1', '2',"3","4","5","6","7","8","9","10","11","12"]
         },
         yAxis: {
            title: {
               text: 'Allocated MegeBytes'
            }
         }
      });
   });
// eventHandlers// @lock

	checkbox2.change = function checkbox2_change (event)// @startlock
	{// @endlock
		// Add your code here
	};// @lock

	button2.click = function button2_click (event)// @startlock
	{// @endlock
		if(WAF.directory.logout());
		$$('tabView2').selectTab(1);
	};// @lock

	imageButton3.click = function imageButton3_click (event)// @startlock
	{// @endlock
		$$('tabView2').selectTab(2);
	};// @lock

	button5.click = function button5_click (event)// @startlock
	{// @endlock
		$$('tabView2').selectTab(2);
	};// @lock

	button4.click = function button4_click (event)// @startlock
	{// @endlock
		$$('tabView2').selectTab(2);
		
		sources.cpuMetrics.query('ID',sources.cpuMetrics.ID + sources.cpuMetrics.length - 24, {
			onSuccess: function(event) {
				var cpuCollection = event.entityCollection;
        	}, params: ["Barrett", "James"]
  	  	});
	};// @lock

	startHistoryTracking.click = function startHistoryTracking_click (event)// @startlock
	{// @endlock
		// Add your code here
	};// @lock

	imageButton2.click = function imageButton2_click (event)// @startlock
	{// @endlock
		$$('tabView2').selectTab(4);
	};// @lock

	imageButton1.click = function imageButton1_click (event)// @startlock
	{// @endlock
		$$('tabView2').selectTab(5);
	};// @lock

	combobox2.change = function combobox2_change (event)// @startlock
	{// @endlock
		var value = $$('#combobox2').getValue();
		if (value == 1) {
//		    sources.Students.toArray("day, percentage", {
//       	 		top = 10, // we want the first ten
//       	 		orderBy: "firstName, courses.matter",
//        		// sorted by first name and by courses in a sub-array
//        		onSuccess: function(event) // asynchronous call
//        		{
//            		myArray = event.result; // we retrieve the array of objects
//            		myArray[1].courses[2].matter; // access the 3rd course of the 2nd student
//        		}
//    		});
		}
		else if (value === 24) {
			sources.memoryMetrics.toArray("month, date, hour,minute, usage", {
       	 		orderBy: "hour",
        		// sorted by first name and by courses in a sub-array
        		onSuccess: function(event) // asynchronous call
        		{
        			$$('richText2').setValue('Memory Monitor');
        			var labelArray = [];
        			var valueArray = [];
            		myArray = event.result; // we retrieve the array of objects
        			
        			for (obj in myArray){
        				if (myArray[obj].month >= 11){// use to control the data range, will add it later;
        					labelArray.push(myArray[obj].hour);
        					valueArray.push(myArray[obj].usage);
        				}
        			}
        			//$$('chart4').recreateChart(labelArray , [ valueArray ] );
        		}
    		});
		}
	};// @lock

	setting.click = function setting_click (event)// @startlock
	{// @endlock
		$$('tabView2').selectTab(3);
	};// @lock

	documentEvent.onLoad = function documentEvent_onLoad (event)// @startlock
	{// @endlock
		if (WAF.directory.currentUser()) {

			//Add data to CPU chart 
        	var cpuData = [];
        	var amazonCPUData = [];
        	var memData = [];
        	var amazonMemData = [];
        	
        	sources.cPU.toArray ("percentage, isFromAmazon", 
			{
			    top: 40, 
			    orderBy: "ID desc", 
			    onSuccess: function(event)  
			    { 
			        var cpuArray = event.result;
			        for (i in cpuArray){
			        	if (cpuArray[i].isFromAmazon != true & cpuData.length < 12)
			        	cpuData.push(cpuArray[i].percentage);
			        	else if (cpuArray[i].isFromAmazon == true & amazonCPUData.length < 12)
			        	amazonCPUData.push(cpuArray[i].percentage);
			        	
			        }
			        chart1.addSeries({
				 		name: 'SaasWatch',
                		data: cpuData        
           			 });
           			 chart1.addSeries({
				 		name: 'Amazon CloudWatch',
                		data: amazonCPUData        
           			 });
           			 
			    }
			});
			
			sources.memory.toArray ("usage, isFromAmazon", 
			{
			    top: 40, 
			    orderBy: "ID desc", 
			    onSuccess: function(event)  
			    { 
			        var memArray = event.result;
			        for (i in memArray){
			        	if (memArray[i].isFromAmazon != true & memData.length < 12)
			        	memData.push(memArray[i].usage);
			        	else if (memArray[i].isFromAmazon == true & amazonMemData.length < 12)
			        	amazonMemData.push(memArray[i].usage);
			        	
			        }
			        chart2.addSeries({
				 		name: 'SaasWatch',
                		data: memData        
           			 });
           			 chart2.addSeries({
				 		name: 'Amazon CloudWatch',
                		data: amazonMemData        
           			 });
           			 
			    }
			});
			
			var diskData = [];
			sources.disk.toArray ("allocated", 
			{
			    top: 12, 
			    orderBy: "ID desc", 
			    onSuccess: function(event)  
			    { 
			        var diskArray = event.result;
			        for (i in diskArray){
			        	diskData.push(diskArray[i].allocated);
			        }
			        chart3.addSeries({
				 		name: 'SaasWatch',
                		data: diskData        
           			 });
			    }
			});
			
			
			
			
			$$('tabView2').selectTab(2);
		}
		
		
		//$$('chart6').recreateChart([ "7" ,  "8" ,  "9","10","11","12","13","14" ] , [ [22, 25, 30,50,10,22,12,0] ] );
		//$$('chart2').recreateChart([ "7" , "8" ,  "9","10","11","12","13","14" ] , [ [22, 25, 30,50,10,22,12,0] ] );
	};// @lock

	passwordField.keyup = function passwordField_keyup (event)// @startlock
	{// @endlock
		console.log(event);
		if (event.keyCode == 13) {
		    var userName = $('#userNameField').val();
		    var password = $('#passwordField').val();
		    $('#errorDiv1').html("");

		    WAF.directory.login(userName, password, {
		        onSuccess: function(result) {
		            if (result.result) {
		                $$('tabView2').selectTab(3)
		            }
		            else {
		                $('#errorDiv1').html("Username or password is incorrect");
		            }
		        },
		        onError: function(result) {
		            $('#errorDiv1').html("Something is wrong, please try it again");
		        }
		    });
		}
	};// @lock

	button1.click = function button1_click (event)// @startlock
	{// @endlock
		var userName = $('#userNameField').val();
		var password = $('#passwordField').val();
		$('#errorDiv1').html("");
		
		WAF.directory.login(userName, password , {
			onSuccess: function(result){
				if(result.result) {
					$$('tabView2').selectTab(3);
				}
				else {
					$('#errorDiv1').html("Username or password is incorrect");
				}
			},
			onError: function(result){$('#errorDiv1').html("Something is wrong, please try it again");}
			});
	};// @lock

// @region eventManager// @startlock
	WAF.addListener("checkbox2", "change", checkbox2.change, "WAF");
	WAF.addListener("button2", "click", button2.click, "WAF");
	WAF.addListener("imageButton3", "click", imageButton3.click, "WAF");
	WAF.addListener("button5", "click", button5.click, "WAF");
	WAF.addListener("button4", "click", button4.click, "WAF");
	WAF.addListener("startHistoryTracking", "click", startHistoryTracking.click, "WAF");
	WAF.addListener("imageButton2", "click", imageButton2.click, "WAF");
	WAF.addListener("imageButton1", "click", imageButton1.click, "WAF");
	WAF.addListener("combobox2", "change", combobox2.change, "WAF");
	WAF.addListener("setting", "click", setting.click, "WAF");
	WAF.addListener("document", "onLoad", documentEvent.onLoad, "WAF");
	WAF.addListener("passwordField", "keyup", passwordField.keyup, "WAF");
	WAF.addListener("button1", "click", button1.click, "WAF");
// @endregion
};// @endlock
