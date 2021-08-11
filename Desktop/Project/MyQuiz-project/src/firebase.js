// firebase.js

import firebase from "firebase/app";
import "firebase/firestore";

const firebaseConfig = {
    apiKey: "AIzaSyDknKXHd_xkpIgpget3g_McLdsA1iTmlC8",
    authDomain: "sparta-react-5e33d.firebaseapp.com",
    projectId: "sparta-react-5e33d",
    storageBucket: "sparta-react-5e33d.appspot.com",
    messagingSenderId: "1030648892424",
    appId: "1:1030648892424:web:15d38766212ee5edcfd5bd",
    measurementId: "G-2SB1PQSZTE"
  };

  firebase.initializeApp(firebaseConfig);

  const firestore = firebase.firestore();

  export { firestore };