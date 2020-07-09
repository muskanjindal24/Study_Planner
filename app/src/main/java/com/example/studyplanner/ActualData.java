package com.example.studyplanner;

public class ActualData {

        public String Rating;
        public String Subject;
        public String Algo;

        public ActualData() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public ActualData(String Rating, String Subject, String Algo) {
            this.Rating = Rating;
            this.Subject = Subject;
            this.Algo=Algo;
        }

}
