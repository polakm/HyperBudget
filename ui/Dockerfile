# base image
FROM node:9.6.1

# Create a work directory and copy over our dependency manifest files.
RUN mkdir /app
WORKDIR /app
COPY /app/src /app/src
COPY /app/public /app/public
COPY ["/app/package.json", "/app/package-lock.json*","/app/yarn.lock", "./"]
#RUN npm install --production
COPY /app/node_modules /app/node_modules


CMD [ "yarn", "start"]
# Expose PORT 3000 on our virtual machine so we can run our server
EXPOSE 3000