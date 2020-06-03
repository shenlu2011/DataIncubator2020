// https://observablehq.com/@d3/learn-d3-by-example@728
import define1 from "./8d5ef3030dfd3bad@249.js";
import define2 from "./450051d7f1174df8@201.js";

export default function define(runtime, observer) {
  const main = runtime.module();
  const fileAttachments = new Map([["values@1.json",new URL("./files/3847c94c6702a07037c13c98420295e8aae20c8a4630edea8543bbf7e8a72c87dd41d455035bc3f229ef3e6736ff476a7f34bcd14f013ae0a9a6c2d45114524f",import.meta.url)],["changing-stuff@1.jpg",new URL("./files/d28dcb2f7ccecddccb458a31296dbf5f290f4915347d845ecec513b55d27e8118eb4706dcd2430bcf702251bb2afa4e1f78ae41359a56c01d2b30559679d9a8b",import.meta.url)]]);
  main.builtin("FileAttachment", runtime.fileAttachments(name => fileAttachments.get(name)));
  main.variable(observer()).define(["md"], function(md){return(
md`# Learn D3: By Example

One of the best ways to start with D3 is to [browse the gallery](/@d3/gallery).`
)});
  main.variable(observer()).define(["md"], function(md){return(
md`If you’re lucky (and with many to choose from, your odds aren’t bad), you may find an example you can repurpose as-is. This can save a lot of effort over learning how to build from scratch. For example, to construct a treemap, you *could* consult the [d3-hierarchy API reference](https://github.com/d3/d3-hierarchy/blob/master/README.md), but much faster would be to take the [treemap example](https://observablehq.com/@d3/treemap) and substitute your data.`
)});
  main.variable(observer()).define(["md"], function(md){return(
md`This may feel like cheating, but it’s okay! 👍 Examples are not merely reusable templates but tools for learning, hinting at subjects for study. “Breaking” an example by tinkering—changing stuff and seeing what happens—helps you achieve understanding faster than passive reading.

Plus, it’s fun. 😺`
)});
  main.variable(observer()).define(["md","FileAttachment"], async function(md,FileAttachment){return(
md`<figure>
  <img src="${await FileAttachment("changing-stuff@1.jpg").url()}" style="width: 274px; height: 360px;">
  <figcaption>A legitimate learning strategy. Image: [DEV Community](https://twitter.com/thepracticaldev)</figcaption>
</figure>`
)});
  main.variable(observer()).define(["md"], function(md){return(
md`Not by coincidence, Observable is designed to help you tinker. Visit any notebook, edit a cell, and see what happens! In many cases all it takes to substitute your data is clicking to replace a file. If you like the result, save your work by forking.`
)});
  main.variable(observer()).define(["md"], function(md){return(
md`You can also import examples and tinker with them without diving into the code. This is undoubtedly the fastest way to start with D3, and it’ll grow more powerful as you learn.`
)});
  main.variable(observer()).define(["md"], function(md){return(
md`Let’s see how.

Say I hand you a set of numbers. What might you tell me about this data?`
)});
  main.variable(observer("values")).define("values", ["FileAttachment"], function(FileAttachment){return(
FileAttachment("values@1.json").json()
)});
  main.variable(observer()).define(["md"], function(md){return(
md`You could inspect a few values by hand, but this won’t reveal much. There’s no way to know if the handful of values you inspect is representative of the overall distribution. What if you computed a few [summary statistics](/@d3/d3-mean-d3-median-and-friends), such as the min, median and max, by [requiring](/@observablehq/introduction-to-require) D3 (version 5)?`
)});
  main.variable(observer("d3")).define("d3", ["require"], function(require){return(
require("d3@5")
)});
  main.variable(observer()).define(["d3","values"], function(d3,values){return(
[d3.min(values), d3.median(values), d3.max(values)]
)});
  main.variable(observer()).define(["md"], function(md){return(
md`Well… that’s something. But we need something richer than a single number to get a sense of the distribution.

We need a visualization. Specifically, a histogram. So let’s [import](/@observablehq/introduction-to-imports) the [histogram example](/@d3/histogram) and use the *with* clause of the import (a feature unique to Observable JavaScript) to substitute our *values* array of numbers as the *data*.`
)});
  const child1 = runtime.module(define1).derive([{name: "values", alias: "data"}], main);
  main.import("chart", "chart1", child1);
  main.variable(observer()).define(["chart1"], function(chart1){return(
chart1
)});
  main.variable(observer()).define(["md","tex"], function(md,tex){return(
md`*Et voilà!* Now we see: the data roughly forms a normal distribution centered on zero. (I created these values using [d3.randomNormal](/@d3/d3-random) with an expected value ${tex`\mu = 0`} and standard deviation ${tex`\sigma = 1`}.)`
)});
  main.variable(observer()).define(["md"], function(md){return(
md`But we’re not limited to substituting data when we import. We can override any cell, say to customize the appearance of the *x*- or *y*-axis. Or if we’d prefer a slightly shorter chart, we can redefine the height.`
)});
  main.variable(observer("height")).define("height", function(){return(
200
)});
  const child2 = runtime.module(define1).derive([{name: "values", alias: "data"},"height"], main);
  main.import("chart", "chart2", child2);
  main.variable(observer()).define(["chart2"], function(chart2){return(
chart2
)});
  main.variable(observer()).define(["md"], function(md){return(
md`Did you notice fewer ticks along the *y*-axis in this shorter chart? Magic! And if you think that’s cool, watch this histogram come alive as we inject *dynamic* data! Click play below or drag the slider.`
)});
  main.variable(observer("viewof mu")).define("viewof mu", ["Scrubber","d3"], function(Scrubber,d3){return(
Scrubber(d3.ticks(-5, 5, 200), {
  format: x => `mu = ${d3.format("+.2f")(x)}`,
  autoplay: false,
  alternate: true
})
)});
  main.variable(observer("mu")).define("mu", ["Generators", "viewof mu"], (G, _) => G.input(_));
  main.variable(observer()).define(["chart3"], function(chart3){return(
chart3
)});
  main.variable(observer()).define(["md"], function(md){return(
md`The *values3* cell below generates random values, while the *x* cell fixes the histogram’s domain to [-10, +10]. Fixing the domain shows how the distribution shifts in response to the mean, *mu*; if a fixed domain were not specified, the domain would be computed to fit the data and the change in distribution would only be apparent by a close reading of the *x*-axis ticks. (To see this yourself, try removing *x* from the import’s with clause below.)`
)});
  main.variable(observer("values3")).define("values3", ["d3","mu"], function(d3,mu){return(
Float64Array.from({length: 2000}, d3.randomNormal(mu, 2))
)});
  main.variable(observer("x")).define("x", ["d3","margin","width"], function(d3,margin,width){return(
d3.scaleLinear([-10, 10], [margin.left, width - margin.right])
)});
  const child3 = runtime.module(define1).derive(["x",{name: "values3", alias: "data"},"height"], main);
  main.import("chart", "chart3", child3);
  main.import("margin", child3);
  main.import("width", child3);
  main.variable(observer()).define(["md"], function(md){return(
md`We didn’t need to rewrite the histogram to make it live because cells referencing *mu*, including imported cells, automatically run when *mu* changes thanks to [dataflow](/@observablehq/how-observable-runs).

Now that you’ve seen how far you can get repurposing examples, let’s write some code from scratch!

<a title="Learn D3: Data" style="display: inline-flex; align-items: center; font: 600 14px var(--sans-serif);" href="/@d3/learn-d3-data?collection=@d3/learn-d3">Next<svg width="8" height="16" fill="none" stroke-width="1.8" style="margin-left: 0.25em; padding-top: 0.25em;"><path d="M2.75 11.25L5.25 8.25L2.75 5.25" stroke="currentColor"></path></svg></a>`
)});
  main.variable(observer()).define(["md"], function(md){return(
md`---

## Appendix

Many Observable notebooks use an appendix to define needed cells, such as requires and imports, without interrupting the flow of reading. There’s nothing special about this section—it’s purely a stylistic convention.`
)});
  const child4 = runtime.module(define2);
  main.import("Scrubber", child4);
  return main;
}
