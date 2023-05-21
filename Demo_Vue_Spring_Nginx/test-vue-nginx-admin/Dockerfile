# #  FROM node:12.2.0-alpine
# #  EXPOSE 8080
# #  WORKDIR /app
# #  COPY package.json /app/package.json
# #  RUN npm install
# #  RUN npm install vue@3.2.26
# #  RUN npm install @vue/cli -g
# #  CMD ["npm", "run", "serve"]


# FROM node:lts-alpine

# # install simple http server for serving static content
# # RUN npm install -g http-server

# # make the 'app' folder the current working directory
# WORKDIR /app

# # copy both 'package.json' and 'package-lock.json' (if available)
# COPY package*.json ./

# # install project dependencies
# RUN npm install

# # copy project files and folders to the current working directory (i.e. 'app' folder)
# COPY . .

# # build app for production with minification
# # RUN npm run build

# EXPOSE 8080
# CMD ["npm", "run", "serve"]
# #CMD ["npm", "run", "build"]

# # production stage
# # FROM nginx:stable-alpine as production-stage
# # COPY --from=build-stage /app/dist /usr/share/nginx/html
# # COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf
# # EXPOSE 80
# # CMD ["nginx", "-g", "daemon off;"]


FROM node:14.18-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY ./ .
RUN npm run build

# production stage
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]