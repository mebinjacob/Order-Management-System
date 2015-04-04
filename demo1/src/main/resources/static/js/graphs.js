/**
 * @author Mebin Jacob
 * using google charts  
 */

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});
      
     

      // Callback that creates and populates a data table, 
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawPieChart() {
      	$.get("/getData", function(dataFromServer){
  		// Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
  	    data.addColumn('number', 'blah');
        data.addRows(dataFromServer);//[['topping', 2], ['blah', 2]]
  	  

        // Set chart options
        var options = {'title':'How Much Pizza I Ate Last Night',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      	  }); 
    }
      
      
      function drawScatterChart() {
    	  $.get("/getPieChartData", function(dataFromServer){
          var data = google.visualization.arrayToDataTable(dataFromServer);/*
          [
          ['Age', 'Weight'],
          [ 8,      12],
          [ 4,      5.5],
          [ 11,     14],
          [ 4,      5],
          [ 3,      3.5],
          [ 6.5,    7]
        ]
          */

          var options = {
            title: 'Age vs. Weight comparison',
            hAxis: {title: 'Age', minValue: 0, maxValue: 15},
            vAxis: {title: 'Weight', minValue: 0, maxValue: 15},
            legend: 'none'
          };

          var chart = new google.visualization.ScatterChart(document.getElementById('piechart_div'));

          chart.draw(data, options);
        });
      }
      
      
      
      
      //Functions below are to load data for inventory manager
      function drawInventoryMgmtPieChart() {
        	$.get("/inventoryManager/getInventoryMgmtPieChartData", function(dataFromServer){
    		// Create the data table.
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Category');
    	    data.addColumn('number', 'Count');
          data.addRows(dataFromServer);//[['topping', 2], ['blah', 2]]
    	  

          // Set chart options
          var options = {'title':'Percentage of items per category',
                         'width':400,
                         'height':300};

          // Instantiate and draw our chart, passing in some options.
          var chart = new google.visualization.PieChart(document.getElementById('ivmgmt_chart_div'));
          chart.draw(data, options);
        	  }); 
      }
      
      
