from visual_chatgpt import  VisualQuestionAnswering
import os
import pandas as pd
from PIL import Image, UnidentifiedImageError

'''
I will be using this script to parse through all the images in my dataset. There are few things to keep in mind:
'''

device = "cpu"
vqa =  VisualQuestionAnswering(device)

image_dir = "/Users/neuro140/Desktop/humor_detection/data/Renamed/"
output_path = "path/to/output/file.txt"
prompt = 'How funny is this image on a scale of 1 to 10?'
ratings = {}

# Loop through each image in the directory

def get_VQA_ratings(dir, prompt, assignments, destination):
    ratings = {}
    faulty_imgs = []
    for image in os.listdir(image_dir):
        #Check that the image is valid
        try:
            Image.open(image_dir + image)
        except UnidentifiedImageError:
            print(f'failed to open{image}')
            continue

        for i in range(0, assignments):
            # Rank the image and store the result
            if not image.startswith('.'):
                input_str = f"{os.path.join(image_dir + image)}, {prompt}"
                rating = vqa.inference(input_str)
                if image not in ratings:
                    ratings[image] = [rating]
                else:
                    ratings[image].append(rating)
        print(f"Done with  {image}")

    df = pd.DataFrame(ratings)
    df.to_csv('/Users/neuro140/Desktop/humor_detection/data/VQA_ratings.csv', index=False)
    return df 



get_VQA_ratings(dir=image_dir, prompt=prompt, assignments=2, destination=output_path)