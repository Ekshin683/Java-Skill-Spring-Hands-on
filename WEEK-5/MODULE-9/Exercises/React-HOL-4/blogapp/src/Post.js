// Post.js — defines the structure of a Post object
// This is a plain JavaScript class (not a React component)
// Used to create Post instances with id, title, and body

class Post {
  constructor(id, title, body) {
    this.id    = id;
    this.title = title;
    this.body  = body;
  }
}

export default Post;