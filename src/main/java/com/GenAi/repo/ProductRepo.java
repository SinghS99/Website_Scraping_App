package com.GenAi.repo;

import com.GenAi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    public List<Product> findByItemProfileContainingOrItemDescContaining(String itemProfile, String itemDesc);
}

/*
List<JobPost> jobs = new ArrayList<>(Arrays.asList(

        new JobPost(1, "D SOFA", "Excellent,Good,Average", 2,
                List.of("Single", "Double", "Triple", "Family pack")),


        new JobPost(2, "Refrigrator", "Excellent,Good,Average", 3,
                List.of("Single", "Double", "Triple", "Family pack")),


        new JobPost(3, "MicroOven", "Excellent,Good,Average", 4,
                List.of("Single", "Double", "Triple", "Family pack")),


        new JobPost(4, "Bed", "Excellent,Good,Average", 5,
                List.of("Single", "Double", "Triple", "Family pack")),


        new JobPost(5, "KingsSize Bed", "Excellent,Good,Average", 3,
                List.of("Single", "Double", "Triple", "Family pack"))
));

// method to return all JobPosts
public List<JobPost> getAllJobs() {
    return jobs;
}

public JobPost getJob(int postId){
    for(JobPost job: jobs ){
        if(job.getPostId()== postId){
            return job;
        }
    }
    return null;
}

// method to save a job post object into arrayList
public void addJob(JobPost job) {
    jobs.add(job);
    System.out.println(jobs);

}


public void updateJob(JobPost jobPost) {
    for(JobPost jobPost1:jobs){
        if(jobPost1.getPostId()==jobPost.getPostId()){
            jobPost1.setPostProfile(jobPost.getPostProfile());
            jobPost1.setPostDesc(jobPost.getPostDesc());
            jobPost1.setReqExperience(jobPost.getReqExperience());
            jobPost1.setPostTechStack(jobPost.getPostTechStack());
        }
    }
}

public void deleteJob(int postId) {
    for(JobPost jobPost: jobs){
        if(jobPost.getPostId()== postId){
            jobs.remove(jobPost);
        }
    }
}*/
