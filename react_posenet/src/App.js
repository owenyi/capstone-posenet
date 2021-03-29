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
    var imageElement = document.getElementById("yoga");
    
    const getAngle = (a, b, c) => {
      const dx1 = c.x - b.x;
      const dx2 = a.x - b.x;
      const dy1 = c.y - b.y;
      const dy2 = a.y - b.y;

      const radians = Math.abs(Math.atan2(dy1, dx1) - Math.atan2(dy2, dx2));
      const angle = radians * 180 / Math.PI;

      return angle;
    }

    var imageKeypoints;

    posenet.load().then(function(net) {
      const pose = net.estimateSinglePose(imageElement, {
        flipHorizontal: true
      });
      return pose;
    }).then(function(pose){
      console.log(pose);
      imageKeypoints = pose.keypoints;
      return imageKeypoints;
    }).then(function(imageKeypoints){
          
        // 5 - leftShoulder
        // 6 - rightShoulder
        // 7 - leftElbow
        // 8 - rightElbow
        // 9 - leftWrist
        // 10 - rightWrist
        // 11 - leftHip
        // 12 - rightHip
        // 13 - leftKnee
        // 14 - rightKnee
        // 15 - leftAnkle
        // 16 - rightAnkle

        const imageAngleRIGHT_ELBOW = getAngle(imageKeypoints[6].position, imageKeypoints[8].position, imageKeypoints[10].position);
        const imageAngleLEFT_ELBOW = getAngle(imageKeypoints[5].position, imageKeypoints[7].position, imageKeypoints[9].position);
        const imageAngleRIGHT_KNEE = getAngle(imageKeypoints[12].position, imageKeypoints[14].position, imageKeypoints[16].position);
        const imageAngleLEFT_KNEE = getAngle(imageKeypoints[11].position, imageKeypoints[13].position, imageKeypoints[15].position);
        console.log(imageAngleRIGHT_ELBOW);
        console.log(imageAngleLEFT_ELBOW);
        console.log(imageAngleRIGHT_KNEE);
        console.log(imageAngleLEFT_KNEE);
    })
  }

  return (
    <div className="App">
      <img id="yoga" src='images/list/yoga1.jpg' crossOrigin='anonymous' onLoad={()=>estimationYogaPage()}/>
    </div>
  )
}

export default App;
