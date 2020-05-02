
var width = 900,
    height = 300,
    pad = 20, 
    left_pad = 100;

var x = d3.scale.ordinal().rangeRoundBands([left_pad, width-pad], 0.1);
var y = d3.scale.linear().range([height-pad, pad]);

var xAxis = d3.svg.axis().scale(x).orient("bottom");
var yAxis = d3.svg.axis().scale(y).orient("left");

var svg = d3.select("#graph")
        .append("svg")
        .attr("width", width)
        .attr("height", height);

d3.json('histogram-hours.json', function (data) {

    data = d3.keys(data).map(function (key) {
        return {bucket: Number(key),
                N: data[key]};
    });

    x.domain(data.map(function (d) { return d.bucket; }));
    y.domain([0, d3.max(data, function (d) { return d.N; })]);

    svg.append("g")
        .attr("class", "axis")
        .attr("transform", "translate(0, "+(height-pad)+")")
        .call(xAxis);

    svg.append("g")
        .attr("class", "axis")
        .attr("transform", "translate("+(left_pad-pad)+", 0)")
        .call(yAxis);


});
