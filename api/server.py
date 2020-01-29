from flask import Flask, request, Response, send_file
import numpy as np
import base64
import cv2
import jsonpickle
import face_recognition
from os import listdir
from os.path import isfile, join, dirname, realpath
imageDir = dirname(realpath(__file__)) + "\\static\\dataset\\"
app = Flask(__name__)


dataName = "UserName"
@app.route('/api/detect',methods = ['POST'])
def test():
    r=request
    data = base64.b64decode(r.data)
    f=open(dirname(realpath(__file__))+"Input.jpg",'wb')
    f.write(data)
    f.close()

    global known_face_encodings
    global known_face_names
    frame=cv2.imread(dirname(realpath(__file__))+"Input.jpg",1)


    #image manipulation
    # Resize frame of video to 1/4 size for faster face recognition processing
    small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)
    # Convert the image from BGR color (which OpenCV uses) to RGB color (which face_recognition uses)
    rgb_small_frame = small_frame[:, :, ::-1]

    


    face_locations = face_recognition.face_locations(rgb_small_frame)
    face_encodings = face_recognition.face_encodings(rgb_small_frame, face_locations)

    face_names = []
    for face_encoding in face_encodings:
        # See if the face is a match for the known face(s)
        matches = face_recognition.compare_faces(known_face_encodings, face_encoding)
        name = "Unknown"

        # Or instead, use the known face with the smallest distance to the new face
        face_distances = face_recognition.face_distance(known_face_encodings, face_encoding)
        best_match_index = np.argmin(face_distances)
        if matches[best_match_index]:
            name = known_face_names[best_match_index]

        face_names.append(name)


    for (top, right, bottom, left), name in zip(face_locations, face_names):
        # Scale back up face locations since the frame we detected in was scaled to 1/4 size
        top *= 4
        right *= 4
        bottom *= 4
        left *= 4

        # Draw a box around the face
        cv2.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)

        # Draw a label with a name below the face
        cv2.rectangle(frame, (left, bottom - 60), (right, bottom), (0, 0, 255), cv2.FILLED)
        font = cv2.FONT_HERSHEY_DUPLEX
        cv2.putText(frame, name, (left+10, bottom - 6), font, 3.0, (255, 255, 255), 3)
        
    
    cv2.imwrite(dirname(realpath(__file__))+"Output.png",frame)
    return send_file('Output.png')

@app.route('/api/dataset/image',methods = ['POST'])
def image():
    r=request
    data = base64.b64decode(r.data)
    f=open(imageDir+dataName+".jpg",'wb')
    f.write(data)
    f.close()
    frame = cv2.imread(imageDir+dataName+".jpg",1)
    reduced_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)
    cv2.imwrite(imageDir+dataName+".jpg",reduced_frame)
    
    dataset=face_recognition.load_image_file(imageDir+dataName+".jpg")
    frame_encoding = face_recognition.face_encodings(dataset)[0]
    known_face_encodings.append(frame_encoding)
    known_face_names.append(dataName)


    response = {'message':'success'}
    response_pickled = jsonpickle.encode(response)
    return Response(response=response_pickled, status=200, mimetype="application/json")


@app.route('/api/dataset/name',methods = ['POST'])
def updateName():
    r=request
    global dataName
    dataName=r.data.decode('ascii')
    response = {'message':'success'}
    response_pickled = jsonpickle.encode(response)
    return Response(response=response_pickled, status=200, mimetype="application/json")

@app.route('/api/download')
def download():
    return send_file(dirname(realpath(__file__))+'Output.png')
if __name__ == '__main__':    
    imageDir = dirname(realpath(__file__)) + "\\static\\dataset\\"
    onlyfiles = [f for f in listdir(imageDir) if isfile(join(imageDir, f))]
    
    known_face_encodings = []
    known_face_names = []
    for i in onlyfiles:
        dataset=face_recognition.load_image_file(imageDir+i)
        frame_encoding = face_recognition.face_encodings(dataset)[0]    
        known_face_encodings.append(frame_encoding)
        known_face_names.append(i[:-4])
    host = input("Enter the IP address of this PC: ")
    port = int(input("Enter the Port number: "))
    print("Hosting API on",host+":"+str(port))
    app.run(host=host, port=port)
