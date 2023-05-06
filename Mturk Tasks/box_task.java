<!-- You must include this JavaScript file -->
<script src="https://assets.crowd.aws/crowd-html-elements.js"></script>

<!-- For the full list of available Crowd HTML Elements and their input/output documentation,
      please refer to https://docs.aws.amazon.com/sagemaker/latest/dg/sms-ui-template-reference.html -->

<!-- You must include crowd-form so that your task submits answers to MTurk -->
<crowd-form answer-format="flatten-objects">
        <crowd-slider name="howMuch" min="1" max="10" step="0.1" pin="true" max-length="25" required></crowd-slider>


    <!-- The crowd-bounding-box element will create a tool for the Worker to draw 
           labeled boxes around the specified objects in your image.

          Your image file URLs will be substituted for the "image_url" variable below 
          when you publish a batch with a CSV input file containing multiple image file URLs.
          To preview the element with an example image, try setting the src attribute to
          "https://s3.amazonaws.com/cv-demo-images/two-birds.jpg" -->
    <crowd-bounding-box 
        src="${image_url}"
        labels="['Funny']"
        header="Draw bounding boxes around what you find funny in the image, if applicable"
        name="annotatedResult">

        <!-- Use the short-instructions section for quick instructions that the Worker
              will see while working on the task. Including some basic examples of 
              good and bad answers here can help get good results. You can include 
              any HTML here. -->
        <short-instructions>Draw boxes around the requested target of interest.</short-instructions>

        <!-- Use the full-instructions section for more detailed instructions that the 
              Worker can open while working on the task. Including more detailed 
              instructions and additional examples of good and bad answers here can
              help get good results. You can include any HTML here. -->
        <full-instructions header="Bounding Box Instructions">
            <p>Use the bounding box tool to draw boxes around the requested target of interest:</p>
            <ol>
              	<li>Draw a rectangle using your mouse over each instance of the target.</li>
                <li>Make sure the box does not cut into the target, leave a 2 - 3 pixel margin</li>
               	<li>When targets are overlapping, draw a box around each object, include all 
                      contiguous parts of the target in the box. Do not include parts that are completely 
                      overlapped by another object.</li>
               	<li>Do not include parts of the target that cannot be seen, even though you think you 
                      can interpolate the whole shape of the target.</li>
               	<li>Avoid shadows, they're not considered as a part of the target.</li>
               	<li>If the target goes off the screen, label up to the edge of the image.</li>
            </ol>
        </full-instructions>

    </crowd-bounding-box>
</crowd-form>