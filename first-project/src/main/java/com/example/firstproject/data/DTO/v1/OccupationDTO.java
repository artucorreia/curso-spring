package com.example.firstproject.data.DTO.v1;

public class OccupationDTO {
    private Long id;
    private String position;
    private String workload;

    public OccupationDTO() {
    }

    public OccupationDTO(Long id, String position, String workload) {
        this.id = id;
        this.position = position;
        this.workload = workload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    @Override
    public String toString() {
        return "OccupationDTO{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", workload='" + workload + '\'' +
                '}';
    }
}
