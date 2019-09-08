package com.saubhik.registration.service;

import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component
public class AWSService {

	public void uploadImage(InputStream imageStream) {
		
		/*
		 *  //Initialize AmazonS3 Instance s3Client.
		 * AmazonS3 s3Client = AmazonS3ClientBuilder.standard() .withCredentials(new
		 * AWSStaticCredentialsProvider(awsCredentials))
		 * .withRegion("paste_your_aws_s3_region_here") .build(); if(s3Client == null) {
		 * 
		 * System.out.println("AWS S3 service not available."); throw new
		 * S3UnAvailableException(); }
		 * 
		 * //Upload Image s3Client.createBucket("my-S3-bucket"); s3Client.putObject(new
		 * PutObjectRequest("my-S3-bucket", key, new File(imageStream)));
		 */
		
	}
	
	
	
}
