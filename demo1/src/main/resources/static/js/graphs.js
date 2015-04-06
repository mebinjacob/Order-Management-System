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
      
      
      function drawSeriesChart() {

          var data = google.visualization.arrayToDataTable([
            ['ID', 'Life Expectancy', 'Fertility Rate', 'Region',     'Population'],
            ['CAN',    80.66,              1.67,      'North America',  33739900],
            ['DEU',    79.84,              1.36,      'Europe',         81902307],
            ['DNK',    78.6,               1.84,      'Europe',         5523095],
            ['EGY',    72.73,              2.78,      'Middle East',    79716203],
            ['GBR',    80.05,              2,         'Europe',         61801570],
            ['IRN',    72.49,              1.7,       'Middle East',    73137148],
            ['IRQ',    68.09,              4.77,      'Middle East',    31090763],
            ['ISR',    81.55,              2.96,      'Middle East',    7485600],
            ['RUS',    68.6,               1.54,      'Europe',         141850000],
            ['USA',    78.09,              2.05,      'North America',  307007000]
          ]);// list of list in java

          var options = {
            title: 'Correlation between life expectancy, fertility rate and population of some world countries (2010)',
            hAxis: {title: 'Life Expectancy'},
            vAxis: {title: 'Fertility Rate'},
            bubble: {textStyle: {fontSize: 11}}
          };

          var chart = new google.visualization.BubbleChart(document.getElementById('series_chart_div'));
          chart.draw(data, options);
        }
      
      
      function histogramChart() {
          var data = google.visualization.arrayToDataTable([
            ['Dinosaur', 'Length'],
            ['Acrocanthosaurus (top-spined lizard)', 12.2],
            ['Albertosaurus (Alberta lizard)', 9.1],
            ['Allosaurus (other lizard)', 12.2],
            ['Apatosaurus (deceptive lizard)', 22.9],
            ['Archaeopteryx (ancient wing)', 0.9],
            ['Argentinosaurus (Argentina lizard)', 36.6],
            ['Baryonyx (heavy claws)', 9.1],
            ['Brachiosaurus (arm lizard)', 30.5],
            ['Ceratosaurus (horned lizard)', 6.1],
            ['Coelophysis (hollow form)', 2.7],
            ['Compsognathus (elegant jaw)', 0.9],
            ['Deinonychus (terrible claw)', 2.7],
            ['Diplodocus (double beam)', 27.1],
            ['Dromicelomimus (emu mimic)', 3.4],
            ['Gallimimus (fowl mimic)', 5.5],
            ['Mamenchisaurus (Mamenchi lizard)', 21.0],
            ['Megalosaurus (big lizard)', 7.9],
            ['Microvenator (small hunter)', 1.2],
            ['Ornithomimus (bird mimic)', 4.6],
            ['Oviraptor (egg robber)', 1.5],
            ['Plateosaurus (flat lizard)', 7.9],
            ['Sauronithoides (narrow-clawed lizard)', 2.0],
            ['Seismosaurus (tremor lizard)', 45.7],
            ['Spinosaurus (spiny lizard)', 12.2],
            ['Supersaurus (super lizard)', 30.5],
            ['Tyrannosaurus (tyrant lizard)', 15.2],
            ['Ultrasaurus (ultra lizard)', 30.5],
            ['Velociraptor (swift robber)', 1.8]]); //again list of list in java

          var options = {
            title: 'Lengths of dinosaurs, in meters',
            legend: { position: 'none' },
          };

          var chart = new google.visualization.Histogram(document.getElementById('chart_div'));
          chart.draw(data, options);
        }
      
      
