d3.json('us-states.json', function(data){


var group = canvas.selectAll('g')
    .data(data.features)
    .enter()
    .append('g')

var projection =  d3.geo.mercator().scale([2400]).translate([-900,2650]);
var path = d3.geo.path().projection(projection);

var areas = group.append('path')
         .attr('d',path)
         .attr('class','area')
         .attr('fill','steelblue');


group.append('text')
     .attr('text',function(d){return d.properties.NAME_1;})
     .attr('x', function(d){ return path.centroid(d)[0];})
     .attr('y', function(d){ return path.centroid(d)[1];})
     .style("font-size","14px");

 };