import * as tf from "@tensorflow/tfjs";
import * as posenet from "@tensorflow-models/posenet";

function App() {
  return (
    <div>
      <YogaImg />
    </div>
  );
}

function YogaImg() {
  function estimationYogaPage() {
    async function estimateMultiplePosesOnImage(imageElement) {

      const net = await posenet.load();
    
      // estimate poses
      const poses = await net.estimateMultiplePoses(imageElement, {
            flipHorizontal: false,
            maxDetections: 2,
            scoreThreshold: 0.6,
            nmsRadius: 20});
    
      return poses;
    }
  
  const imageElement = document.getElementById('yoga');  
  console.log(imageElement);
  
  const poses = estimateMultiplePosesOnImage(imageElement);

  console.log(poses);
  }

  return (
    <div className="App">
      <img id="yoga" src='images/1st/yoga1.png' crossOrigin='anonymous' onLoad={()=>estimationYogaPage()}/>
    </div>
  )
}

export default App;
