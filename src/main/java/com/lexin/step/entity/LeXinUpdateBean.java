package com.lexin.step.entity;

import java.util.List;

public class LeXinUpdateBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean{
        private int active;
        private int calories;
        private String created;
        private int dataSource;
        private String dayMeasurementTime;
        private String deviceId;
        private int distance;
        private String id;
        private int isUpload;
        private String measurementTime;
        private int priority;
        private int step;
        private int type;
        private long updated;
        private String userId;
        private int DataSource;
        private int exerciseTime;

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getDataSource() {
            return dataSource;
        }

        public void setDataSource(int dataSource) {
            this.dataSource = dataSource;
        }

        public int getExerciseTime() {
            return exerciseTime;
        }

        public void setExerciseTime(int exerciseTime) {
            this.exerciseTime = exerciseTime;
        }

        public String getDayMeasurementTime() {
            return dayMeasurementTime;
        }

        public void setDayMeasurementTime(String dayMeasurementTime) {
            this.dayMeasurementTime = dayMeasurementTime;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsUpload() {
            return isUpload;
        }

        public void setIsUpload(int isUpload) {
            this.isUpload = isUpload;
        }

        public String getMeasurementTime() {
            return measurementTime;
        }

        public void setMeasurementTime(String measurementTime) {
            this.measurementTime = measurementTime;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getUpdated() {
            return updated;
        }

        public void setUpdated(long updated) {
            this.updated = updated;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
