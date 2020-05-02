var svg = d3.select('#graph') 
			.append('svg') 
			.style('width', 1024) 
			.style('height', 768);


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