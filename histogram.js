
var width = 900,
    height = 300,
    pad = 20, 
    left_pad = 100;

var x = d3.scale.ordinal().rangeRoundBands([left_pad, width-pad], 0.1);
var y = d3.scale.linear().range([height-pad, pad]);

var xAxis = d3.svg.axis().scale(x).orient("bottom");
var yAxis = d3.svg.axis().scale(y).orient("left");

var svg = d3.select('#graph') .append('svg') .style('width', 1024) .style('height', 768);


svg.append('text') 
    .text("A picture!") 
    .attr({x: 10,
            y: 150,
            'text-anchor': 'start'});

svg.append('line') 
    .attr({x1: 10,
            y1: 10,
            x2: 100,
            y2: 100,
            stroke: 'blue', 'stroke-width': 3});


