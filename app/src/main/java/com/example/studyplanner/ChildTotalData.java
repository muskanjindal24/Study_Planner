package com.example.studyplanner;

public class ChildTotalData {

        public String Hr;
        public String Sub;

        public ChildTotalData() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public ChildTotalData(String Hr, String Sub) {
            this.Hr = Hr;
            this.Sub = Sub;
        }

}
