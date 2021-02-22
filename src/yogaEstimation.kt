person.numKeypoints = 17 // person class에 들어있음

val minConfidence = 0.5
val videoPositions = Array(numKeypoints) { Position() }

// Draw key points over the image.
for (keyPoint in person.keyPoints) {
    if (keyPoint.score > minConfidence) {
        val position = keyPoint.position
        val adjustedX: Float = screenWidth-(position.x.toFloat() * widthRatio + left)
        val adjustedY: Float = position.y.toFloat() * heightRatio +top
        canvas.drawCircle(adjustedX, adjustedY, circleRadius, paint)
    }
}

// imgae
// cloudfront에서 이미지 불러오기
// MongoDB에서 각도 불러오기
var imageAngleRIGHT_ELBOW = 불러온 각도
var imageAngleLEFT_ELBOW = 불러온 각도
var imageAngleRIGHT_KNEE = 불러온 각도
var imageAngleLEFT_KNEE = 불러온 각도

// video
for (i in 0..person.numKeypoints - 1) {
    if (person.keyPoints[i].score > minConfidence) {
        videoChecks[i] = true
        val position = person.keyPoints[i].position
        val adjustedX: Float = screenWidth-(videoPositions[i].x.toFloat() * widthRatio + left)
        val adjustedY: Float = videoPositions[i].y.toFloat() * heightRatio +top
        videoPositions[i].x = adjustedX
        videoPositions[i].y = adjustedY
        canvas.drawCircle(adjustedX, adjustedY, circleRadius, paint)
    }
}

//0    NOSE,
//1    LEFT_EYE,
//2    RIGHT_EYE,
//3    LEFT_EAR,
//4    RIGHT_EAR,
//5    LEFT_SHOULDER,
//6    RIGHT_SHOULDER,
//7    LEFT_ELBOW,
//8    RIGHT_ELBOW,
//9    LEFT_WRIST,
//10    RIGHT_WRIST,
//11    LEFT_HIP,
//12    RIGHT_HIP,
//13    LEFT_KNEE,
//14    RIGHT_KNEE,
//15    LEFT_ANKLE,
//16    RIGHT_ANKLE

var videoAngleRIGHT_ELBOW = 0
var videoAngleLEFT_ELBOW = 0
var videoAngleRIGHT_KNEE = 0
var videoAngleLEFT_KNEE = 0

var errorRateRIGHT_ELBOW = 100
var errorRateLEFT_ELBOW = 100
var errorRateRIGHT_KNEE = 100
var errorRateLEFT_KNEE = 100

if (videoChecks[6] and videoChecks[8] and videoChecks[10]) {
    videoAngleRIGHT_ELBOW = getAngle(videoPositions[6], videoPositions[8], videoPositions[10])
    errorRateRIGHT_ELBOW = round((abs(imageAngleRIGHT_ELBOW - videoAngleRIGHT_ELBOW)) / imageAngleRIGHT_ELBOW * 100) * 10 / 10
}
if (videoChecks[5] and videoChecks[7] and videoChecks[9]) {
    videoAngleLEFT_ELBOW = getAngle(videoPositions[5], videoPositions[7], videoPositions[9])
    errorRateLEFT_ELBOW = round((abs(imageAngleLEFT_ELBOW - videoAngleLEFT_ELBOW)) / imageAngleLEFT_ELBOW * 100) * 10 / 10
}
if (videoChecks[12] and videoChecks[14] and videoChecks[16]) {
    videoAngleRIGHT_KNEE = getAngle(videoPositions[12], videoPositions[14], videoPositions[16])
    errorRateRIGHT_KNEE = round((abs(imageAngleRIGHT_KNEE - videoAngleRIGHT_KNEE)) / imageAngleRIGHT_KNEE * 100) * 10 / 10
}
if (videoChecks[11] and videoChecks[13] and videoChecks[15]) {
    videoAngleLEFT_KNEE = getAngle(videoPositions[11], videoPositions[13], videoPositions[15])
    errorRateLEFT_KNEE = round((abs(imageAngleLEFT_KNEE - videoAngleLEFT_KNEE)) / imageAngleLEFT_KNEE * 100) * 10 / 10
}

// correct?
if (errorRateRIGHT_ELBOW < 20 and errorRateLEFT_ELBOW < 20 and errorRateRIGHT_KNEE < 20 and errorRateLEFT_KNEE < 20) {
    // 정답임을 유저에게 알려주고 다음으 이미지로 넘어가도록
}