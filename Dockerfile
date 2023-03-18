FROM node:lts-alpine3.12
WORKDIR /gate-simulator/app
COPY . .
RUN npm install
CMD ["npm", "start"]
EXPOSE 9999