// https://observablehq.com/@d3/zoom-to-bounding-box@146
export default function define(runtime, observer) {
  const main = runtime.module();
  const fileAttachments = new Map([["states-albers-10m.json",new URL("./files/75faaaca1f1a4f415145b9db520349a3a0b93a53c1071346a30e6824586a7c251f45367d9262ed148b7a2b5c2694aa7703f3ac88051abc65066fd0074fdf9c9e",import.meta.url)]]);
  main.builtin("FileAttachment", runtime.fileAttachments(name => fileAttachments.get(name)));
  main.variable(observer()).define(["md"], function(md){return(
md`# Zoom to Bounding Box

Pan and zoom, or click to zoom into a particular state using [*zoom*.transform](https://github.com/d3/d3-zoom/blob/master/README.md#zoom_transform) transitions. The bounding box is computed using [*path*.bounds](https://github.com/d3/d3-geo/blob/master/README.md#path_bounds).`
)});
 

main.variable(observer()).define("d3", require("d3@5"))
main.variable(observer()).define("path", d3.geoPath())
main.variable(observer()).define("us", FileAttachment("states-albers-10m.json").json())
main.variable(observer()).define("topojson", require("topojson-client@3"))


  main.variable(observer("chart")).define("chart", ["d3","topojson","us","path"], function(d3,topojson,us,path)
{
  const width = 975;
  const height = 610;

  const zoom = d3.zoom()
      .scaleExtent([1, 8])
      .on("zoom", zoomed);

  const svg = d3.create("svg")
      .attr("viewBox", [0, 0, width, height])
      .on("click", reset);

  const g = svg.append("g");

  g.append("g")
      .attr("fill", "#444")
      .attr("cursor", "pointer")
    .selectAll("path")
    .data(topojson.feature(us, us.objects.states).features)
    .join("path")
      .on("click", clicked)
      .attr("d", path)
    .append("title")
      .text(d => d.properties.name);

  g.append("path")
      .attr("fill", "none")
      .attr("stroke", "white")
      .attr("stroke-linejoin", "round")
      .attr("d", path(topojson.mesh(us, us.objects.states, (a, b) => a !== b)));

  svg.call(zoom);

  function reset() {
    svg.transition().duration(750).call(
      zoom.transform,
      d3.zoomIdentity,
      d3.zoomTransform(svg.node()).invert([width / 2, height / 2])
    );
  }

  function clicked(d) {
    const [[x0, y0], [x1, y1]] = path.bounds(d);
    d3.event.stopPropagation();
    svg.transition().duration(750).call(
      zoom.transform,
      d3.zoomIdentity
        .translate(width / 2, height / 2)
        .scale(Math.min(8, 0.9 / Math.max((x1 - x0) / width, (y1 - y0) / height)))
        .translate(-(x0 + x1) / 2, -(y0 + y1) / 2),
      d3.mouse(svg.node())
    );
  }

  function zoomed() {
    const {transform} = d3.event;
    g.attr("transform", transform);
    g.attr("stroke-width", 1 / transform.k);
  }

  return svg.node();
}
);
  
  return main;
}
